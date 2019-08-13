package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jk.model.LogModel;
import com.jk.service.MongoService;
import com.jk.util.DataGridResult;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("mongodb")
public class MongoController {

    @Reference
    private MongoService mongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("queryLogList")
    @ResponseBody
    public DataGridResult queryLogList(LogModel logModel, Integer page, Integer rows){


        PageUtil pageUtil = mongoService.queryLogList(page,rows);

        DataGridResult result = new DataGridResult();

        result.setRows(pageUtil.getList());
        result.setTotal(pageUtil.getSumSize());

        return result;
    }

}
