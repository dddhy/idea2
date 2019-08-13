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


    @RequestMapping("index")
    public String index(){

        return "index";
    }

}
