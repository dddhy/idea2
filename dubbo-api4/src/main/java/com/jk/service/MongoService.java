package com.jk.service;

import com.jk.util.PageUtil;

public interface MongoService {


    PageUtil queryLogList(Integer page, Integer rows);
}
