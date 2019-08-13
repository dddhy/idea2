package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.model.LogModel;
import com.jk.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public PageUtil queryLogList(Integer page, Integer rows) {
        Criteria c = new Criteria();
        Query query = new Query();
        query.addCriteria(c);
        //总条数
        long sumsize = mongoTemplate.count(query, LogModel.class,"logdb2");

        PageUtil pageUtil = new PageUtil((int) sumsize,page,rows);
        //开始条数
        Integer skip = pageUtil.getFirstResultCount();
        query.skip(skip);
        query.limit(rows);

        List<LogModel> list = mongoTemplate.find(query,LogModel.class,"logdb2");

        pageUtil.setList(list);


        return pageUtil;
    }
}
