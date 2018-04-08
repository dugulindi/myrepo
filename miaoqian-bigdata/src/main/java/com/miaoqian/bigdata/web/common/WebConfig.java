package com.miaoqian.bigdata.web.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lind
 * DATETIME 2016/11/22.10:45
 */
public class WebConfig {
    private static String ip;
    private static String port;
    private static String project;
    private static String BD_URI;

    static {
        Properties prop = new Properties();
        InputStream in = WebConfig.class.getClassLoader().getResourceAsStream("web-servlet.properties");
        try {
            prop.load(in);
            ip = prop.getProperty("ip").trim();
            port = prop.getProperty("port").trim();
            project = prop.getProperty("project").trim();
            BD_URI = "http://" + ip + ":" + port + "/" + project;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        WebConfig.ip = ip;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        WebConfig.port = port;
    }

    public static String getProject() {
        return project;
    }

    public static void setProject(String project) {
        WebConfig.project = project;
    }

    public static String getBdUri() {
        return BD_URI;
    }

    public static void setBdUri(String bdUri) {
        BD_URI = bdUri;
    }
}
