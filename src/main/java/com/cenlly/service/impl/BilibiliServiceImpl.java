package com.cenlly.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cenlly.domain.*;
import com.cenlly.service.IBilibiliService;
import com.cenlly.utils.CookiesUtils;
import com.cenlly.utils.FileUtils;
import com.cenlly.utils.request.RequestHelper;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class BilibiliServiceImpl implements IBilibiliService {

    @Override
    public void startAutoCoinsJob(String path) {
        System.setProperty("webdriver.chrome.driver", path);
        ChromeOptions options = new ChromeOptions();
        ChromeDriver webDriver = new ChromeDriver(options);
        String url = "https://passport.bilibili.com/login";
        webDriver.get(url); //
        // 与浏览器同步非常重要，必须等待浏览器加载完毕
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //中间完成登录操作
        System.out.println("浏览器初始化成功，请在浏览器登录成功后输入\"start\"开启任务");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.next().equals("start")) {
                break;
            }
        }
        saveLog("开始自动投币任务");
        while (true) {
            try {
                saveLog("刷新界面中");
                webDriver.navigate().refresh();
                webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                Set<Cookie> cookie_list = webDriver.manage().getCookies();
                String cookies = CookiesUtils.pushCookies(cookie_list);
                Calendar now = Calendar.getInstance();
                int time = now.get(Calendar.HOUR_OF_DAY);
                if (time >= 23) {
                    int request_num = 0;
                    while (request_num < 10) {
                        if (request_num != 0) {
                            Thread.sleep(300000);
                            webDriver.navigate().refresh();
                            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                        }
                        request_num++;
                        saveLog("尝试投币中，次数：" + request_num);
                        saveLog("获取今日投币数量");
                        int coins_count = getCoinNum(cookies);
                        if (coins_count == -1) {
                            saveLog("获取今日投币数量失败");
                            continue;
                        }
                        saveLog("今日投币数量：" + coins_count);
                        if (coins_count < 5) {
                            saveLog("未完成今日任务，开始投币");
                            List<Channel> channels = FileUtils.getChannelList();
                            int channel_index = getRandom(getRandom(channels.size()));
                            Channel channel = channels.get(channel_index);
                            saveLog("随机获取频道：" + channel.getName());
                            DataModelBase video_data = RequestHelper.getNewVideoByChannelId(channel.getId());
                            if (video_data == null) {
                                saveLog("获取视频信息失败");
                                continue;
                            }
                            if (video_data.getCode().equals("0")) {
                                JSONArray videos = ((JSONObject) video_data.getData()).getJSONArray("list");
                                List<VideoItem> videoItems = videos.toJavaList(VideoItem.class);
                                int video_index = getRandom(getRandom(videoItems.size()));
                                if (video_index == 0) {
                                    saveLog("获取视频信息失败");
                                    continue;
                                }
                                VideoItem video = videoItems.get(video_index);
                                saveLog("随机获取视频：" + video.getBvid() + "--------" + video.getName());
                                DataModelBase job_data = RequestHelper.addCoins(cookies, video);
                                if (job_data == null) {
                                    saveLog("投币失败：" + video.getBvid());
                                    continue;
                                }
                                if (job_data.getCode().equals("0")) {
                                    saveLog("投币成功：" + video.getBvid());
                                } else {
                                    saveLog(job_data.getMessage() + " " + video.getBvid());
                                }
                            }

                        } else {
                            saveLog("已完成今日任务");
                            break;
                        }
                    }
                }
                Thread.sleep(600000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static int getRandom(int count) {
        Random random = new Random();
        return random.nextInt(count);
    }

    private static void saveLog(String msg) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("[" + simpleDateFormat.format(new Date()) + "]");
        System.out.println(msg);
        System.out.println();
    }

    private static int getCoinNum(String cookies) {
        DataModelBase dataModelBase = RequestHelper.getCoinsLog(cookies);
        if (dataModelBase == null) {
            return -1;
        }
        if (!dataModelBase.getCode().equals("0")) {
            return -1;
        }
        JSONArray jsonArray = ((JSONObject) dataModelBase.getData()).getJSONArray("list");
        List<CoinsLog> coinsLogs = jsonArray.toJavaList(CoinsLog.class);
        Calendar now = Calendar.getInstance();
        int now_data = now.get(Calendar.DAY_OF_YEAR);
        int count = 0;
        for (CoinsLog log : coinsLogs) {
            Calendar log_date = Calendar.getInstance();
            log_date.setTime(log.getTime());
            if (log_date.get(Calendar.DAY_OF_YEAR) == now_data && log.getDelta() < 0) {
                count -= log.getDelta();
            }
        }
        return count;
    }
}
