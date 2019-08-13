package com.jk.controller;

import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("showZhuxing")
    public String showZhuxing() {

        return "showZhuxing";
    }

    @RequestMapping("showYuan")
    public String showYuan() {

        return "showYuan";
    }

    @RequestMapping("showZhexian")
    public String showZhexian() {

        return "showZhexian";
    }

    @RequestMapping("showQipao")
    public String showQipao() {

        return "showQipao";
    }


    @RequestMapping("queryZhuxing")
    @ResponseBody
    public List<Map<String, Object>> tiaoxing() {

        List<Map<String, Object>> list = carService.queryZhuxing();
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (Map<String, Object> map1 : list) {
            Map<String, Object> map = new HashMap<>();

            String aa = map1.get("颜色").toString();
            Integer count = Integer.parseInt(map1.get("总个数").toString());

            String sj = (map1.get("时间").toString());

            map.put("name", map1.get("时间"));
            int[] bb = new int[]{count};
            map.put("data", bb);

            list1.add(map);
        }

        return list1;
    }

    @RequestMapping("queryYuan")
    @ResponseBody
    public List<Map<String, Object>> queryYuan() {
        System.err.println(123);
        List<Map<String, Object>> list = carService.queryYuan();
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map1 : list) {
            Map<String, Object> map = new HashMap<>();
            Integer object = Integer.parseInt(map1.get("颜色").toString());

            if (object == 1) {
                map.put("name", map1.get("月") + "白");
            } else if (object == 2) {
                map.put("name", map1.get("月") + "蓝");
            } else {
                map.put("name", map1.get("月") + "红");
            }

            map.put("y", map1.get("总个数"));
            map.put("sliced", "true");
            map.put("selected", "true");
            list1.add(map);
        }
        return list1;

    }

    @RequestMapping("queryZhexian")
    @ResponseBody
    public List<Map<String, Object>> queryZhexian() {
        System.out.println(123);
        List<Map<String, Object>> list = carService.queryZhexian();
        List<Map<String, Object>> list1 = new ArrayList<>();

        for (Map<String, Object> map1 : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("name", map1.get("月份"));
            map.put("y", map1.get("总个数"));
            list1.add(map);
        }
        return list1;
    }

    @RequestMapping("queryQipao")
    @ResponseBody
    public List<Map<String, Object>> queryQipao() {
        List<Map<String, Object>> list = carService.querySandian();
        List<Map<String, Object>> list1 = new ArrayList<>();
        for (Map<String, Object> map1 : list) {
            Map<String, Object> map = new HashMap<>();
            Integer object = Integer.parseInt(map1.get("颜色").toString());
            if (object == 1) {
                map.put("name", "蓝色");
            } else if (object == 2) {
                map.put("name", "白色");
            } else if (object == 3) {
                map.put("name", "黑色");
            } else {
                map.put("name", "红色");
            }


            Integer aa = Integer.parseInt(map1.get("日").toString());
            Integer bb = Integer.parseInt(map1.get("总个数").toString());

            System.out.println(aa);
            System.out.println(bb);
            int[] cc = new int[]{aa, bb};
            map.put("data", cc);


            list1.add(map);

        }

        return list1;


    }
}