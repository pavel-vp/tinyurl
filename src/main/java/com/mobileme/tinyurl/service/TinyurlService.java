package com.mobileme.tinyurl.service;

import com.mobileme.tinyurl.api.TinyurlDao;
import com.mobileme.tinyurl.api.TinyurlAPI;
import com.mobileme.tinyurl.model.AllUrlResponse;
import com.mobileme.tinyurl.model.CreateTinyurlResponse;
import com.mobileme.tinyurl.model.TinyurlConfig;
import com.mobileme.tinyurl.util.UrlGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TinyurlService implements TinyurlAPI {

    private TinyurlDao tinyurlDao;

    @Autowired
    public TinyurlService(TinyurlDao tinyurlDao) {
        this.tinyurlDao = tinyurlDao;
    }

    @Override
    public CreateTinyurlResponse create(String url) {
        try {
            String tinyurl = UrlGenerator.generateUrl(url);
            String finalTinyurl = tinyurl;
            int i = 0;
            // check if exists
            while (!tinyurlDao.safeAdd(finalTinyurl, url) && i < TinyurlConfig.CHAR_TOTAL) {
                finalTinyurl = UrlGenerator.addCharToUrl(tinyurl, i);
                i++;
            }
            return new CreateTinyurlResponse(finalTinyurl, "ok");
        } catch (Exception e) {
            e.printStackTrace();
            return new CreateTinyurlResponse("error for url", "");
        }
    }

    @Override
    public String getUri(String tinyurl) {
        return tinyurlDao.getFillUrl(tinyurl);
    }

    @Override
    public AllUrlResponse all() {
        return null;
    }

    @Override
    public void delete(String tinyurl) {

    }
}
