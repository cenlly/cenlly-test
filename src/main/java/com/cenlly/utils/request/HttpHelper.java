package com.cenlly.utils.request;


import com.alibaba.fastjson.JSON;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpHelper {
    private static CloseableHttpClient httpClient;

    public static String get(String url, Map<String, Object> params, Map<String, String> heads) throws Exception {

        httpClient = HttpClientBuilder.create().build();

        if (params != null) {
            url += "?";
            for (String key : params.keySet()) {
                url = url + key + "=" + params.get(key).toString() + "&";
            }
            url = url.substring(0, url.length() - 1);
        }
        HttpGet httpGet = new HttpGet(url);


        if (heads != null) {
            for (String key : heads.keySet()) {
                httpGet.setHeader(key, heads.get(key));
            }
        }

        RequestConfig requestConfig = RequestConfig.custom()
                // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)
                // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)
                // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)
                // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(true).build();

        // 将上面的配置信息 运用到这个Get请求里
        httpGet.setConfig(requestConfig);

        CloseableHttpResponse response = httpClient.execute(httpGet);
        HttpEntity responseEntity = response.getEntity();

       if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
           return EntityUtils.toString(responseEntity);
       } else {
           throw new Exception("http get failed msg:\n"+response.getStatusLine().getStatusCode());
       }

    }


    public static String post(String url, Map<String, Object> params, Map<String, String> heads) throws Exception {
        httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        if (params != null) {
            List<BasicNameValuePair> param = new ArrayList<>();
            for (String key : params.keySet()) {
                param.add(new BasicNameValuePair(key,params.get(key).toString()));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(param, StandardCharsets.UTF_8);
            httpPost.setEntity(formEntity);
        }

        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");

        if (heads != null) {
            for (String key : heads.keySet()) {
                httpPost.setHeader(key, heads.get(key));
            }
        }

        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity responseEntity = response.getEntity();

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            return EntityUtils.toString(responseEntity);
        } else {
            throw new Exception("http get failed msg:\n"+response.getStatusLine().getStatusCode());
        }

    }
}
