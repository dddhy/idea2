package com.jk.controller;

import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.UserService;
import com.jk.util.TreeNoteUtil;
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

        List<User> list = userService.query();

        return list;
    }

    @RequestMapping("test")

    public String test(){
        return "test";
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public void deleteUser(String id){
        System.err.println(id);
        userService.deleteUser(id);
    }

    @RequestMapping("addUser")
    @ResponseBody
    public void addUser(User user,String userid){
        if(user.getUserid()==null){
            userService.addUser(user);
            return;
        }
        System.err.println(userid);
        userService.updateUser(user);
    }

    @RequestMapping("getTreeAll")
    @ResponseBody
    public List<Tree> getTreeAll(Tree tree){

        List<Tree> list = userService.getTreeAll();

        list = TreeNoteUtil.getFatherNode(list);

        return list;
    }

}
