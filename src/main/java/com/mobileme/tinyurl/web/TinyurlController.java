package com.mobileme.tinyurl.web;

import com.mobileme.tinyurl.api.TinyurlAPI;
import com.mobileme.tinyurl.model.AllUrlResponse;
import com.mobileme.tinyurl.model.CreateTinyurlResponse;
import com.mobileme.tinyurl.service.TinyurlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;
import java.util.logging.Level;

@Controller("/")
public class TinyurlController implements TinyurlAPI {

    private Logger logger = Logger.getLogger(TinyurlController.class.getName());

    private TinyurlService tinyurlService;

    @Autowired
    public TinyurlController(TinyurlService tinyurlService) {
        this.tinyurlService = tinyurlService;
    }

    @Override
    @PostMapping("create")
    public @ResponseBody CreateTinyurlResponse create(@RequestBody String url) {
        logger.log(Level.INFO, "create url = " + url);
        return tinyurlService.create(url);
    }

    @Override
    @GetMapping("{tinyurl}")
    public String getUri(@PathVariable("tinyurl") String tinyurl) {
        logger.log(Level.INFO, "get for tinyurl = " + tinyurl);
        String fullUrl = tinyurlService.getUri(tinyurl);
        if (fullUrl != null) {
            return "redirect:" + fullUrl;
        } else {
            return "redirect:errorpage";
        }
    }

    @Override
    public AllUrlResponse all() {
        return null;
    }

    @Override
    public void delete(String tinyurl) {

    }
}
