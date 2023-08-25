package com.maxim.coupons.utils;

import com.maxim.coupons.threads.SendStatisticsThread;

public class StatisticsUtils extends Thread {

    public static void sendStatistics(String text) {
        SendStatisticsThread sendStatisticsThread = new SendStatisticsThread(text);
        sendStatisticsThread.start();
    }
}
