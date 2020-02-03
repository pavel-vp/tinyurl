package com.mobileme.tinyurl.dao;

import com.mobileme.tinyurl.api.TinyurlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class TinyurlDaoPGImpl implements TinyurlDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public TinyurlDaoPGImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public boolean safeAdd(String finalTinyurl, String fullUrl) {
        String q = "insert into tinyurls (tinyurl, fullurl) values (:tinyurl, :fullurl); ";
        Map<String, Object> map = new HashMap<>();
        map.put("tinyurl", finalTinyurl);
        map.put("fullurl", fullUrl);
        try {
            int count = namedParameterJdbcTemplate.update(q, map);
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getFillUrl(String tinyurl) {
        String q = "select fullurl from tinyurls where tinyurl = :tinyurl; ";
        Map<String, Object> map = new HashMap<>();
        map.put("tinyurl", tinyurl);
        return namedParameterJdbcTemplate.queryForObject(q,map, String.class);
    }
}
