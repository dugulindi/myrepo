package com.miaoqian.bigdata.hive.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by lind
 * DATETIME 2016/11/22.10:45
 */
public class HiveConfig {
    private static String hiveUser;
    private static String hiveJdbcUrl;
    private static String impalaJdbcUrl;
    private static String fsDefault;
    private static String hiveDb;
    private static String hiveTable;

    static {
        Properties prop = new Properties();
        InputStream in = HiveConfig.class.getClassLoader().getResourceAsStream("hive.properties");
        try {
            prop.load(in);
            hiveUser = prop.getProperty("hive.user").trim();
            hiveJdbcUrl = prop.getProperty("hive.jdbc.url").trim();
            impalaJdbcUrl = prop.getProperty("impala.jdbc.url").trim();
            fsDefault = prop.getProperty("fs.default").trim();
            hiveDb = prop.getProperty("hive.db").trim();
            hiveTable = prop.getProperty("hive.table").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHiveUser() {
        return hiveUser;
    }

    public static void setHiveUser(String hiveUser) {
        HiveConfig.hiveUser = hiveUser;
    }

    public static String getHiveJdbcUrl() {
        return hiveJdbcUrl;
    }

    public static void setHiveJdbcUrl(String hiveJdbcUrl) {
        HiveConfig.hiveJdbcUrl = hiveJdbcUrl;
    }

    public static String getImpalaJdbcUrl() {
        return impalaJdbcUrl;
    }

    public static void setImpalaJdbcUrl(String impalaJdbcUrl) {
        HiveConfig.impalaJdbcUrl = impalaJdbcUrl;
    }

    public static String getFsDefault() {
        return fsDefault;
    }

    public static void setFsDefault(String fsDefault) {
        HiveConfig.fsDefault = fsDefault;
    }

    public static String getHiveDb() {
        return hiveDb;
    }

    public static void setHiveDb(String hiveDb) {
        HiveConfig.hiveDb = hiveDb;
    }

    public static String getHiveTable() {
        return hiveTable;
    }

    public static void setHiveTable(String hiveTable) {
        HiveConfig.hiveTable = hiveTable;
    }
}
