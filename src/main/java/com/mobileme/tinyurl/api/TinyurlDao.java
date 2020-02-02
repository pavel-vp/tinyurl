package com.mobileme.tinyurl.api;

public interface TinyurlDao {

    boolean safeAdd(String finalTinyurl, String fullUrl);


    String getFillUrl(String tinyurl);
}
