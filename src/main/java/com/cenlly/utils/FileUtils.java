package com.cenlly.utils;


import com.alibaba.fastjson.JSONArray;
import com.cenlly.ApplicationRun;
import com.cenlly.domain.Channel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileUtils {
    public static List<Channel> getChannelList() {
        try {
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("data.json");
            Scanner sc = new Scanner(in, "UTF-8");
            StringBuilder data = new StringBuilder();
            while (sc.hasNextLine()) {
                data.append(sc.nextLine());
            }
            return JSONArray.parseArray(data.toString(), Channel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
