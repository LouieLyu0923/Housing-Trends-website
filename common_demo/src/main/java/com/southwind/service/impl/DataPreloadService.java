package com.southwind.service.impl;

import com.southwind.service.HouseInformationService;
import com.southwind.vo.HouseInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class DataPreloadService {

    @Autowired
    private HouseInformationService service;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Async
    public void preloadMapData() {
        String cacheKey = "houseDataMap";

        // Check if the Redis Hash exists
        Boolean hasCache = redisTemplate.hasKey(cacheKey);

        if (Boolean.FALSE.equals(hasCache)) {
            // Retrieve all houses from the database
            List<HouseInformationVO> allHouse = service.queryAllHouse(null, null, null, null, null, null, null, null, null, null);

            // Create maps to store the total price and count of houses for each district
            Map<String, Float> totalPrices = new HashMap<>();
            Map<String, Integer> houseCounts = new HashMap<>();

            // Iterate over the houses and calculate the total price and count of houses for each district
            for (HouseInformationVO house : allHouse) {
                String district = house.getUrbanArea();
                float price = house.getPrice();

                totalPrices.put(district, totalPrices.getOrDefault(district, 0.0f) + price);
                houseCounts.put(district, houseCounts.getOrDefault(district, 0) + 1);
            }

            // Store the average price per square meter for each district in the Redis Hash
            for (String district : totalPrices.keySet()) {
                float totalPrice = totalPrices.get(district);
                int houseCount = houseCounts.get(district);
                float averagePricePerSqMeter = totalPrice / houseCount;

                // Store in Redis Hash with district as the key and average price as the value
                redisTemplate.opsForHash().put(cacheKey, district + "区", String.valueOf(averagePricePerSqMeter));
            }

            // Set an expiration time for the Redis Hash (optional, if you want it to expire)
            redisTemplate.expire(cacheKey, 1, TimeUnit.HOURS);
        }
    }
}