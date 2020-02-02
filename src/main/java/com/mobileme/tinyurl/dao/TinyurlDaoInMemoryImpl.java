package com.mobileme.tinyurl.dao;

import com.mobileme.tinyurl.api.TinyurlDao;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TinyurlDaoInMemoryImpl implements TinyurlDao {


    private Map<String, String> map = new ConcurrentHashMap<>();

    @Override
    public boolean safeAdd(String tinyurl, String fullUrl) {
        if (map.containsKey(tinyurl)) return false;
        map.put(tinyurl, fullUrl);
        return true;
    }

    @Override
    public String getFillUrl(String tinyurl) {
        return map.get(tinyurl);
    }
}
