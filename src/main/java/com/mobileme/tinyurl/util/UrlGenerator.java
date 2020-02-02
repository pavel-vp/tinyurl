package com.mobileme.tinyurl.util;

import com.mobileme.tinyurl.model.TinyurlConfig;

public class UrlGenerator {

    private static String prepareUrl(String url) {
        int length = url.length();
        int left = length % TinyurlConfig.TINYURL_MAX;
        StringBuilder urlAdded = new StringBuilder(url);
        if (left > 0) {
            for (int i = 0; i < (TinyurlConfig.TINYURL_MAX - left); i++) {
                urlAdded.append(" ");
            }
        }
        return urlAdded.toString();
    }

    public static String generateUrl(String url) {
        StringBuilder sb = new StringBuilder();
        int length = url.length();
        String urlAligned = prepareUrl(url);

        for (int i = 0; i < TinyurlConfig.TINYURL_MAX; i++) {
            int b = (length / TinyurlConfig.TINYURL_MAX) * i;
            int e = b + (length / TinyurlConfig.TINYURL_MAX);
            String sub = urlAligned.substring(b, e);
            int hashCode = sub.hashCode() % TinyurlConfig.CHAR_TOTAL;
            sb.append(encode(hashCode));
        }
        return sb.toString();
    }

    private static char encode(int hashCode) {
        if (hashCode < TinyurlConfig.CHAR_TOTAL) {
            if (hashCode <= TinyurlConfig.CHAR_az_END) {
                return (char) (hashCode + 'a');
            } else {
                if (hashCode <= TinyurlConfig.CHAR_AZ_END) {
                    return (char) (hashCode - TinyurlConfig.CHAR_AZ_START + 'A');
                } else {
                    return (char) (hashCode - TinyurlConfig.CHAR_0_START + '0');
                }
            }
        }
        throw new IllegalStateException("Can't encode character");
    }

    public static String addCharToUrl(String tinyurl, int i) {
        return tinyurl + encode(i);
    }
}
