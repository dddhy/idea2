package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.User;
import com.jk.service.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("query")
    @ResponseBody
    public Map<Object, Object> query(Integer page, Integer rows){

        Map map = new HashMap();

        map.put("start",(page-1)*rows);
        map.put("end",rows);
        Integer count = userService.queryCount(map);
        List<User> list = userService.query(map);
        Map<Object,Object> map2 = new HashMap<>();
        map2.put("rows",list);
        map2.put("total",count);
        return map2;
    }


  @RequestMapping("deleteUser")
    @ResponseBody
    public void deleteUser(Integer id){

        userService.deleteUser(id);
    }
}
