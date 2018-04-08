package com.miaoqian.bigdata.search.util;

import com.miaoqian.bigdata.common.constants.LogLevel;
import com.miaoqian.bigdata.common.constants.Platform;
import com.miaoqian.bigdata.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by lind
 * DATETIME 2016/11/28.10:29
 */
public class LogParamUtils {


    public static Date getBeginDate(String beginTime) throws ParseException {
        Date begin = null;
        if (StringUtils.isNotEmpty(beginTime)) {
            begin = DateUtils.getSimpeFormat().parse(beginTime);

        } else {
            begin = DateUtils.getYyyymmddFormat().parse("2016-01-01");
        }
        return begin;
    }

    public static Date getEndDate(String endTime) throws ParseException {
        Date end = null;
        if (StringUtils.isNotEmpty(endTime)) {
            end = DateUtils.getSimpeFormat().parse(endTime);
        } else {
            end = new Date();
        }
        return end;
    }

    public static Collection<String> getModules(String userModules, String moduleName) {
        Collection<String> modules = new ArrayList<String>();
        if ("ALL".equalsIgnoreCase(moduleName)) {
            if ("ALL".equalsIgnoreCase(userModules)) {
                for (Platform platform : Platform.values()) {
                    modules.add(platform.name());
                }
            } else {
                for (String module : userModules.split(",")) {
                    modules.add(module.trim());
                }
            }
        } else {
            modules.add(moduleName);
        }
        return modules;
    }

    public static Collection<String> getLogLevels(String logLevel) {
        Collection<String> levels = new ArrayList<String>();
        if ("ALL".equalsIgnoreCase(logLevel)) {
            for (LogLevel level : LogLevel.values()) {
                levels.add(level.name());
            }
        } else {
            levels.add(logLevel);
        }
        return levels;
    }
}
