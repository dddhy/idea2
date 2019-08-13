package com.jk.service.impl;

import com.jk.dao.CarDao;
import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<Map<String, Object>> queryZhuxing() {

        return carDao.queryZhuxing();
    }

    @Override
    public List<Map<String, Object>> queryYuan() {
        return carDao.queryYuan();
    }

    @Override
    public List<Map<String, Object>> queryZhexian() {
        return carDao.queryZhexian();
    }

    @Override
    public List<Map<String, Object>> querySandian() {
        return carDao.querySandian();
    }
}
