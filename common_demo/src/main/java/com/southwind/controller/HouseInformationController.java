package com.southwind.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.gson.Gson;
import com.southwind.entity.HouseInformation;
import com.southwind.entity.SysUser;
import com.southwind.service.HouseInformationService;
import com.southwind.util.PageUtil;
import com.southwind.vo.HouseInformationVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
import java.util.*;

import static java.util.Collections.max;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-05-25
 */
@RestController
@RequestMapping("/house")
public class HouseInformationController {

    @Autowired
    private HouseInformationService service;

    //工具方法
    private static float median(List<Float> list) {
        float s = 0;

        Collections.sort(list);
        int size = list.size();
        if(size % 2 != 1){
            s = (float) ((list.get(size/2-1) + list.get(size/2)+0.0)/2);//加0.0是为了计算是将int类型转换为浮点型

        }else {
            s = list.get((size-1)/2);
        }
        return s;
    }

    private static float calculateAverage(List<Float> values) {
        if (values.isEmpty()) {
            return 0.0f;  // Handle the case where the list is empty to avoid division by zero
        }

        float sum = 0.0f;
        for (float value : values) {
            sum += value;
        }

        return sum / values.size();
    }




    @GetMapping("/findAllHouse")
    public List<HouseInformation> findAllHouse(){
        return service.findAllHouse();
    }

    @GetMapping("/list")
    public ModelAndView list(
            String key1,//来明确指定该参数不是必需的。这样，如果key1的值为空字符串，后端将接收到null而不是0。
            String key2,
            String key3,
            String key4,
            String key5,
            String key6,
            String key7,
            String key8,
            String key9,
            String key10,
            @RequestParam(value = "page",required = false) Integer page
    ){
        if (key1 == null||key1.isEmpty()) {
            key1 = null;
        }
        if(page == null) page = 1;
        ModelAndView modelAndView = new ModelAndView();
        IPage<HouseInformationVO> pageUtil = this.service.queryHouse(key1,key2,key3,key4,key5,key6,key7,key8,key9,key10,page);

        modelAndView.addObject("list",pageUtil.getRecords());
        modelAndView.addObject("page", page);
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= pageUtil.getPages(); i++) {
            pages.add(i);
        }
        modelAndView.addObject("pages", pages);
        modelAndView.addObject("pageCount", pageUtil.getPages());
        modelAndView.addObject("total", pageUtil.getTotal());
        modelAndView.addObject("start", (page-1)*PageUtil.SIZE + 1);
        Integer end = page*PageUtil.SIZE;
        if(end > pageUtil.getTotal()) {
            modelAndView.addObject("end", pageUtil.getTotal());
        } else {
            modelAndView.addObject("end", end);
        }
        modelAndView.addObject("key1", key1);
        modelAndView.addObject("key2", key2);
        modelAndView.addObject("key3", key3);
        modelAndView.addObject("key4", key4);
        modelAndView.addObject("key5", key5);
        modelAndView.addObject("key6", key6);
        modelAndView.addObject("key7", key7);
        modelAndView.addObject("key8", key8);
        modelAndView.addObject("key9", key9);
        modelAndView.addObject("key10", key10);
        List<HouseInformationVO> houseList = pageUtil.getRecords();

        List<Integer> idList = new ArrayList<>();
        List<Float> priceList = new ArrayList<>();

        for (HouseInformationVO house : houseList) {
            idList.add(house.getId());
        }

        for (HouseInformationVO house : houseList) {
            priceList.add(house.getPrice());
        }



        modelAndView.addObject("idList", idList);
        modelAndView.addObject("priceList", priceList);




        //House of all Pages
        List<HouseInformationVO> allHouse = this.service.queryAllHouse(key1,key2,key3,key4,key5,key6,key7,key8,key9,key10);


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



        //算每日的选择出来的数据平均每平方米的价格
        Map<String, List<Float>> priceAreaMap = new HashMap<>();
        for (int i = 0; i < allDateList.size(); i++) {
            String date = allDateList.get(i);
            float pricePerArea = allPriceList.get(i);
            if (priceAreaMap.containsKey(date)) {
                priceAreaMap.get(date).add(pricePerArea);
            } else {
                List<Float> priceAreaList = new ArrayList<>();
                priceAreaList.add(pricePerArea);
                priceAreaMap.put(date, priceAreaList);
            }
        }

        List<List<Object>> pricePerDay = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : priceAreaMap.entrySet()) {
            String date = entry.getKey();
            List<Float> priceAreaList = entry.getValue();
            float average = calculateAverage(priceAreaList);

            List<Object> rowData = new ArrayList<>();
            rowData.add("\""+date+"\"");
            rowData.add(average);

            // Find the corresponding ID using the index from allIdList
            int index = allDateList.indexOf(date);
            if (index >= 0 && index < allIdList.size()) {
                String id = ""+allIdList.get(index);
                rowData.add(id);
            } else {
                rowData.add(""); // or any default value if ID is not found
            }
            pricePerDay.add(rowData);
        }


        //算每日的选择出来的数据最高价每平方米的价格
        Map<String, List<Float>> priceAreaMap_ = new HashMap<>();
        for (int i = 0; i < allDateList.size(); i++) {
            String date = allDateList.get(i);
            float pricePerArea = allPriceList.get(i);
            if (priceAreaMap_.containsKey(date)) {
                priceAreaMap_.get(date).add(pricePerArea);
            } else {
                List<Float> priceAreaList = new ArrayList<>();
                priceAreaList.add(pricePerArea);
                priceAreaMap_.put(date, priceAreaList);
            }
        }
        List<List<Object>> priceMaxPerDay = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : priceAreaMap_.entrySet()) {
            String date = entry.getKey();
            List<Float> priceAreaList = entry.getValue();
            Float max = max(priceAreaList);
            List<Object> rowData = new ArrayList<>();
            rowData.add("\""+date+"\"");
            rowData.add(max);

            // Find the corresponding ID using the index from allIdList
            int index = allDateList.indexOf(date);
            if (index >= 0 && index < allIdList.size()) {
                String id = ""+allIdList.get(index);
                rowData.add(id);
            } else {
                rowData.add(""); // or any default value if ID is not found
            }
            priceMaxPerDay.add(rowData);
        }


        //算每日的选择出来的数据最低价每平方米的价格

        List<List<Object>> priceMinPerDay = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : priceAreaMap.entrySet()) {
            String date = entry.getKey();
            List<Float> priceAreaList = entry.getValue();
            Float min = Collections.min(priceAreaList);
            List<Object> rowData = new ArrayList<>();
            rowData.add("\""+date+"\"");
            rowData.add(min);

            // Find the corresponding ID using the index from allIdList
            int index = allDateList.indexOf(date);
            if (index >= 0 && index < allIdList.size()) {
                String id = ""+allIdList.get(index);
                rowData.add(id);
            } else {
                rowData.add(""); // or any default value if ID is not found
            }
            priceMinPerDay.add(rowData);
        }

        //算每日的选择出来的数据中位数价每平方米的价格
        List<List<Object>> priceMidPerDay = new ArrayList<>();
        for (Map.Entry<String, List<Float>> entry : priceAreaMap.entrySet()) {
            String date = entry.getKey();
            List<Float> priceAreaList = entry.getValue();

            Collections.sort(priceAreaList);

            int size = priceAreaList.size();
            float median;

            if (size % 2 == 1) {
                // Odd-sized list
                median = priceAreaList.get(size / 2);
            } else {
                // Even-sized list
                float value1 = priceAreaList.get(size / 2 - 1);
                float value2 = priceAreaList.get(size / 2);
                median = (value1 + value2) / 2;
            }


            List<Object> rowData = new ArrayList<>();
            rowData.add("\""+date+"\"");
            rowData.add(median);

            // Find the corresponding ID using the index from allIdList
            int index = allDateList.indexOf(date);
            if (index >= 0 && index < allIdList.size()) {
                String id = ""+allIdList.get(index);
                rowData.add(id);
            } else {
                rowData.add(""); // or any default value if ID is not found
            }
            priceMidPerDay.add(rowData);
        }

// Calculate the average price per area for the entire dataset
        float sumPricePerArea = 0;
        int count = 0;
        for (List<Float> priceAreaList : priceAreaMap.values()) {
            for (float pricePerArea : priceAreaList) {
                sumPricePerArea += pricePerArea;
                count++;
            }
        }
        float averagePricePerArea = sumPricePerArea / count;

// Calculate the index values (计算平均数为指数1000的各个时间段的指数)
        List<List<Object>> priceIndexList = new ArrayList<>();
        for (List<Object> rowData : pricePerDay) {
            String date = ((String) rowData.get(0)).replaceAll("\"", "");;

            float pricePerDay_ = (Float) rowData.get(1);

            // 计算每个时期的指数值
            float priceIndex = (pricePerDay_ / averagePricePerArea) * 1000;

            // 将结果添加到列表中
            List<Object> priceIndexData = new ArrayList<>();
            priceIndexData.add(date);
            priceIndexData.add(Math.round(priceIndex)); // 四舍五入到整数
            priceIndexList.add(priceIndexData);
        }
        //将priceIndexList按时间顺序重新排列
        priceIndexList.sort(Comparator.comparing(o -> (String) o.get(0)));
// Convert indexList to JSON string
        Gson gson = new Gson();
        String priceIndexListJson = gson.toJson(priceIndexList);
// Calculate momList (环比)
        List<List<Object>> momList = new ArrayList<>();
        for (int i = 30; i < priceIndexList.size(); i++) {
            float currentPrice = ((Number) priceIndexList.get(i).get(1)).floatValue();
            float prevPrice = ((Number) priceIndexList.get(i - 30).get(1)).floatValue();

            float mom = ((currentPrice - prevPrice) / prevPrice) * 100;
            // Create a list to store the date and mom value
            List<Object> momData = new ArrayList<>();
            momData.add(priceIndexList.get(i).get(0)); // Add the date as the first element
            momData.add(mom);
            momList.add(momData);
        }
// Calculate yoyList (同比)
        List<List<Object>> yoyList = new ArrayList<>();
        for (int i = 365; i < priceIndexList.size(); i++) {
            float currentPrice = ((Number) priceIndexList.get(i).get(1)).floatValue();
            float prevYearPrice = ((Number) priceIndexList.get(i - 365).get(1)).floatValue();
            float yoy = ((currentPrice - prevYearPrice) / prevYearPrice) * 100;

            // Create a list to store the date and yoy value
            List<Object> yoyData = new ArrayList<>();
            yoyData.add(priceIndexList.get(i).get(0)); // Add the date as the first element
            yoyData.add(yoy);
            yoyList.add(yoyData);
        }


// Convert momList and yoyList to JSON strings
        String momListJson = gson.toJson(momList);
        String yoyListJson = gson.toJson(yoyList);

        modelAndView.addObject("yoyListJson",yoyListJson);
        modelAndView.addObject("momListJson",momListJson);
        modelAndView.addObject("priceIndexListJson",priceIndexListJson);













        String allHouseListJson = gson.toJson(allHouseList);

        modelAndView.addObject("allPriceList", allPriceList);
        modelAndView.addObject("allIdList", allIdList);
        modelAndView.addObject("allHouseList",allHouseListJson);
        modelAndView.addObject("pricePerDay",pricePerDay);
        modelAndView.addObject("priceMaxPerDay",priceMaxPerDay);
        modelAndView.addObject("priceMinPerDay",priceMinPerDay);
        modelAndView.addObject("priceMidPerDay",priceMidPerDay);

        Float maxPricePerArea = null;
        if (!allAreaList.isEmpty()) {
            List<Float> pricePerAreaList = allPriceList;
            maxPricePerArea = max(pricePerAreaList);
            // Format the maxPricePerArea to keep only one decimal place
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            maxPricePerArea = Float.parseFloat(decimalFormat.format(maxPricePerArea));
        }
        modelAndView.addObject("maxPricePerArea", maxPricePerArea);

        Float minPricePerArea = null;
        if (!allAreaList.isEmpty()) {
            List<Float> pricePerAreaList = allPriceList;
            minPricePerArea = Collections.min(pricePerAreaList);
            // Format the maxPricePerArea to keep only one decimal place
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            minPricePerArea = Float.parseFloat(decimalFormat.format(minPricePerArea));
        }
        modelAndView.addObject("minPricePerArea", minPricePerArea);

        Float midPricePerArea = null;
        if (!allAreaList.isEmpty()) {
            List<Float> pricePerAreaList = allPriceList;
            midPricePerArea = median(pricePerAreaList);
            // Format the maxPricePerArea to keep only one decimal place
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            midPricePerArea = Float.parseFloat(decimalFormat.format(midPricePerArea));
        }
        modelAndView.addObject("midPricePerArea", midPricePerArea);

        Integer count_ = allPriceList.size();
        modelAndView.addObject("count", count_);


        modelAndView.setViewName("house_list");
        return modelAndView;
    }
}

