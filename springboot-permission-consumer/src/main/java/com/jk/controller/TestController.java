package com.jk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

    @RequestMapping("query")
    public String query(){

        return "list";

    }

    @RequestMapping("login")
    public String login(){

        return "login";

    }

    @RequestMapping("user")
    public String user(){

        return "user";

    }

    @RequestMapping("role")
    public String role(){

        return "role";

    }

    @RequestMapping("log")
    public String log(){

        return "log";
    }

    @RequestMapping("caidan")
    public String caidan(){

        return "caidan";

    }


    @RequestMapping("index")
    public String index(){

        return "index";
    }

}
