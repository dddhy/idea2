package com.jk.dao;

import java.util.List;
import java.util.Map;

public interface CarDao {

    List<Map<String, Object>> queryZhuxing();

    List<Map<String, Object>> queryYuan();

    List<Map<String, Object>> queryZhexian();

    List<Map<String, Object>> querySandian();
}
