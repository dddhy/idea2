package com.jk.controller;

import com.jk.model.User;
import com.jk.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("query")
    @ResponseBody
    public List<User> query(){
        System.out.println(321);
        List<User> list = userService.query(map);
        return list;
    }

  /*  @RequestMapping("query2")
    @ResponseBody
    public List<User> query2(){
        System.out.println(321);
        List<User> list = userService.query2();
        return list;
    }*/



/*    @RequestMapping("add")
    @ResponseBody
    public void add(User user){

        userService.add(user);
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public void deleteUser(String id){

        userService.deleteUser(id);

    }*/


}
