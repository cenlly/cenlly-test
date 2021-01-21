package com.cenlly;

import com.cenlly.service.IBilibiliService;
import com.cenlly.service.impl.BilibiliServiceImpl;

public class ApplicationRun {
    public static void main(String[] args) {
        IBilibiliService bilibiliService = new BilibiliServiceImpl();
        if(args.length == 0) {
            bilibiliService.startAutoCoinsJob("D:/ChromeDriver/chromedriver.exe");
        } else {
            bilibiliService.startAutoCoinsJob(args[0]);
        }
    }
}
