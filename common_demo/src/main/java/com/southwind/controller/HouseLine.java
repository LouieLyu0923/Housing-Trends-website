package com.southwind.controller;


import com.southwind.service.HouseInformationService;
import com.southwind.vo.HouseInformationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/echarts_line")
public class HouseLine {

    @Autowired
    private HouseInformationService service;

    private static List<List<Object>> returnOutput(Map<String, List<List<Object>>> outputRes, String district) {
        List<List<Object>> districtData = outputRes.getOrDefault("\"" + district + "\"", new ArrayList<>());

        // Calculate the average price per square meter for each day
        Map<String, List<Float>> pricesByDate = new HashMap<>();
        for (List<Object> entry : districtData) {
            String date = (String) entry.get(0);
            Float pricePerSqMeter = (Float) entry.get(1);

            if (!pricesByDate.containsKey(date)) {
                pricesByDate.put(date, new ArrayList<>());
            }
            pricesByDate.get(date).add(pricePerSqMeter);
        }

        // Calculate the average price per square meter for each day and sort by time
        List<List<Object>> result = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : pricesByDate.entrySet()) {
            String date = entry.getKey();
            List<Float> prices = entry.getValue();

            // Calculate the average price per square meter for the day
            float averagePrice = (float) prices.stream().mapToDouble(Float::doubleValue).average().orElse(0.0);
            List<Object> dataEntry = new ArrayList<>();
            dataEntry.add(date);
            dataEntry.add(averagePrice);
            result.add(dataEntry);
        }

        // Sort the result by time
        result.sort((a, b) -> ((String) a.get(0)).compareTo((String) b.get(0)));

        return result;
    }


    @GetMapping
    public ModelAndView list() {

        //House of all Pages
        List<HouseInformationVO> allHouse = this.service.queryAllHouse(null, null, null, null,null,null,null,null,null,null);
        List<Float> allPriceList = new ArrayList<>();
        List<Float> allAreaList = new ArrayList<>();
        List<Integer> allIdList = new ArrayList<>();
        List<String> allHouseList = new ArrayList<>();
        List<String> allDateList = new ArrayList<>();
        List<String> allDistrictList = new ArrayList<>();
        for (HouseInformationVO house : allHouse) {
            allPriceList.add(house.getPrice());
            allAreaList.add(house.getHouseArea());
            allDateList.add(house.getDate());
            allIdList.add(house.getId());
            allDistrictList.add(house.getUrbanArea());
            String idHouse = house.getId() + " : " + house.getHouseName();
            allHouseList.add(idHouse);
        }

        // Create a map to store the results
        Map<String, List<List<Object>>> outputRes = new HashMap<>();

// Iterate over the data and calculate the average price per square meter for each date and district
        for (int i = 0; i < allPriceList.size(); i++) {
            String district = allDistrictList.get(i);
            String date = allDateList.get(i);
            Float price = allPriceList.get(i);
            Float area = allAreaList.get(i);

            // Calculate the price per square meter
            Float pricePerSqMeter = price;

            // Check if the district exists in the map, if not, create a new entry
            if (!outputRes.containsKey("\"" + district + "\"")) {
                outputRes.put("\"" + district + "\"", new ArrayList<>());
            }

            // Add the data to the respective district entry in the map
            List<Object> entry = new ArrayList<>();
            entry.add("\"" + date + "\""); // Add the date value with backslash escapes
            entry.add(pricePerSqMeter);
            outputRes.get("\"" + district + "\"").add(entry);
        }

        // Print the results for each district
        ModelAndView modelAndView_v2 = new ModelAndView();
        modelAndView_v2.addObject("panyu", returnOutput(outputRes, "番禺"));
        modelAndView_v2.addObject("tianhe", returnOutput(outputRes, "天河"));
        modelAndView_v2.addObject("yuexiu", returnOutput(outputRes, "越秀"));
        modelAndView_v2.addObject("haizhu", returnOutput(outputRes, "海珠"));
        modelAndView_v2.addObject("liwan", returnOutput(outputRes, "荔湾"));
        modelAndView_v2.addObject("baiyun", returnOutput(outputRes, "白云"));
        modelAndView_v2.addObject("huangpu", returnOutput(outputRes, "黄埔"));
        modelAndView_v2.addObject("huadu", returnOutput(outputRes, "花都"));
        modelAndView_v2.addObject("nansha", returnOutput(outputRes, "南沙"));
        modelAndView_v2.addObject("conghua", returnOutput(outputRes, "从化"));
        modelAndView_v2.addObject("zengcheng", returnOutput(outputRes, "增城"));
        modelAndView_v2.setViewName("echarts_line");
        return modelAndView_v2;
    }
}
