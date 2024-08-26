package com.southwind.controller;

import com.southwind.service.HouseInformationService;
import com.southwind.vo.HouseInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping
    public List<Map<String, Object>> list() {
        String cacheKey = "houseDataMap";
        int maxRetry = 10;
        int retry = 0;
        List<Map<String, Object>> result = new ArrayList<>();

        while (retry < maxRetry) {
            if(redisTemplate.hasKey(cacheKey)) {
                // Retrieve all entries from the Redis Hash
                Map<Object, Object> districtDataMap = redisTemplate.opsForHash().entries(cacheKey);
                if (districtDataMap != null && !districtDataMap.isEmpty()) {
                    // Iterate over each entry in the Redis Hash
                    for (Map.Entry<Object, Object> entry : districtDataMap.entrySet()) {
                        String district = (String) entry.getKey();
                        String averagePricePerSqMeter = (String) entry.getValue();

                        // Create a map to represent each district and its average price
                        Map<String, Object> districtMap = new HashMap<>();
                        districtMap.put("district", district);
                        districtMap.put("averagePricePerSqMeter", averagePricePerSqMeter);
                        result.add(districtMap);
                    }
                }
                return result;
            }
            retry++;
            try {
                // Wait before the next retry
                Thread.sleep(500); // Sleep for 500 milliseconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        return result;
    }
}