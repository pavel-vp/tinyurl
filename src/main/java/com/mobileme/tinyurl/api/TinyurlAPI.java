package com.mobileme.tinyurl.api;

import com.mobileme.tinyurl.model.AllUrlResponse;
import com.mobileme.tinyurl.model.CreateTinyurlResponse;

public interface TinyurlAPI {

    CreateTinyurlResponse create(String url);

    String getUri(String tinyurl);

    AllUrlResponse all();

    void delete(String tinyurl);


}
