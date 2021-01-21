package com.cenlly.utils;

import com.alibaba.fastjson.JSONObject;
import org.openqa.selenium.Cookie;

import java.util.Set;

public class CookiesUtils {

    public static String pushCookies(Set<Cookie> cookies) {
        String str = "";
        for (Cookie cookie : cookies) {
            if (cookie.getDomain().equals(".bilibili.com") || cookie.getDomain().equals("api.bilibili.com")) {
                str += cookie.getName() + "=" + cookie.getValue() + "; ";
            }
        }
        return str.substring(0,str.length()-1);
    }

    public static String getCookie(String cookies, String key) {
        cookies = cookies.replaceAll("\\s*", "");
        try {
            String[] list = cookies.split(";");
            JSONObject jsonObject = new JSONObject();
            for (String item : list) {
                String[] keys = item.split("=");
                jsonObject.put(keys[0], keys[1]);
            }
            return jsonObject.get(key).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
