package com.mobileme.tinyurl;

import com.mobileme.tinyurl.api.TinyurlAPI;
import com.mobileme.tinyurl.api.TinyurlDao;
import com.mobileme.tinyurl.dao.TinyurlDaoInMemoryImpl;
import com.mobileme.tinyurl.model.CreateTinyurlResponse;
import com.mobileme.tinyurl.service.TinyurlService;
import com.mobileme.tinyurl.util.UrlGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TinyurlCreateTest {

    TinyurlAPI tinyurlService;
    TinyurlDao tinyurlDao;

    @Before
    public void setUp() {
        tinyurlDao = new TinyurlDaoInMemoryImpl();
        tinyurlService = new TinyurlService(tinyurlDao);
    }

    @Test
    public void generator_google_url_test() {
        String originalUrl = "https://www.google.com";

        String tinyurl = UrlGenerator.generateUrl(originalUrl);

        Assert.assertEquals("2B5UXi", tinyurl);
    }

    @Test
    public void generator_long_url_test() {
        String originalUrl = "https://mail.google.com/mail/u/0/#inbox/FMfcgxwGDDmJlSQhvpqRTHJfcSTvbwpm";

        String tinyurl = UrlGenerator.generateUrl(originalUrl);

        Assert.assertEquals("Y9flwG", tinyurl);
    }



    @Test
    public void create_one_correct_url_test() {
        String originalUrl = "https://www.google.com";

        CreateTinyurlResponse response = tinyurlService.create(originalUrl);

        Assert.assertNotNull(response);
        Assert.assertEquals("ok", response.getMessage());
        Assert.assertTrue(response.getTinyurl().length() < 8);

    }

    @Test
    public void create_one_add_same_url_test() {
        String originalUrl = "https://www.google.com";

        CreateTinyurlResponse response = tinyurlService.create(originalUrl);

        Assert.assertNotNull(response);
        Assert.assertEquals("ok", response.getMessage());
        Assert.assertTrue(response.getTinyurl().length() < 8);

    }


}
