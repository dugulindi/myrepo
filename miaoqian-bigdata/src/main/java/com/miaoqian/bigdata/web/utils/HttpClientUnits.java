package com.miaoqian.bigdata.web.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientUnits {
    public static String post(String url, String params) throws IOException {
        StringEntity entity = new StringEntity(params, "utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entity);
        HttpResponse res = null;
        String result = "";
        res = HttpClients.createDefault().execute(httpPost);
        result = EntityUtils.toString(res.getEntity());
        return result;
    }

    public static String get(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        HttpResponse res = null;
        String result = "";
        res = HttpClients.createDefault().execute(httpGet);
        result = EntityUtils.toString(res.getEntity());
        return result;
    }

}