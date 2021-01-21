package com.cenlly.utils.request;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cenlly.domain.DataModelBase;
import com.cenlly.domain.VideoItem;
import okhttp3.Headers;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class RequestHelper {
    private static final String BASE_URL = "https://api.bilibili.com";


    public static DataModelBase getNewVideoByChannelId(String channelId) {
        String url = BASE_URL + "/x/web-interface/web/channel/multiple/list";
        Map<String, Object> params = new HashMap<>();
        params.put("channel_id", channelId);
        params.put("sort_type", "new");
        params.put("page_size", 30);
        try {
            String str =HttpHelper.get(url,  params, null);
            return JSON.parseObject(str, DataModelBase.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataModelBase addCoins(String cookies, VideoItem videoItem) {

        String url = BASE_URL + "/x/web-interface/coin/add";
        Map<String, Object> params = new HashMap<>();
        params.put("aid", videoItem.getId());
        params.put("multiply", "1");
        params.put("select_like", "1");
        params.put("cross_domain", "true");
        params.put("csrf", getCookie(cookies, "bili_jct"));

        String referer = "https://www.bilibili.com/video/" + videoItem.getBvid();
        Map<String, String> heads = new HashMap<>();
        heads.put("cookie", cookies);
        heads.put("referer", referer);

        try {
            String str = HttpHelper.post(url, params, heads);
            return JSON.parseObject(str, DataModelBase.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public static DataModelBase getReward(String cookies) {
        String url = BASE_URL + "/x/member/web/exp/reward";
        Map<String, String> heads = new HashMap<>();
        heads.put("cookie", cookies);
        try {
            String str = HttpHelper.get(url,null,heads);
            return JSON.parseObject(str, DataModelBase.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static DataModelBase getCoinsLog(String cookies) {
        String url = BASE_URL + "/x/member/web/coin/log?jsonp=jsonp";
        Map<String, String> heads = new HashMap<>();
        heads.put("cookie", cookies);
        try {
            String str = HttpHelper.get(url,null,heads);
            return JSON.parseObject(str, DataModelBase.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
