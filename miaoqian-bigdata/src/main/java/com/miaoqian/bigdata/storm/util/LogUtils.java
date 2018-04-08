package com.miaoqian.bigdata.storm.util;

/**
 * Created by lind
 * DATETIME 2016/3/29.14:28
 */
public class LogUtils {

    public static LogInfo parseLog(String log) {
        String[] infos = log.split("\t");
        String logTime = infos[0].split(" ")[0] + " " + infos[0].split(" ")[1];
        LogInfo logInfo = new LogInfo(logTime, infos[1], infos[2], infos[3], infos[4], infos[5], infos[6], infos[7], infos[8]);
        return logInfo;
    }
}
