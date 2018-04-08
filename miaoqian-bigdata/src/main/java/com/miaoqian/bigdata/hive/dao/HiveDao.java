package com.miaoqian.bigdata.hive.dao;

import com.miaoqian.bigdata.hive.common.HiveConfig;
import org.springframework.data.hadoop.hive.HiveTemplate;

import java.sql.*;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/22.11:00
 */
public abstract class HiveDao<T> {
    protected static String defaultDb = HiveConfig.getHiveDb();
    protected static String defaultTable = HiveConfig.getHiveTable();
    protected static ResultSet rs;
    protected static Connection connection = null;
    protected PreparedStatement psmt = null;
    protected static Statement stmt = null;
    protected static final String DRIVER_NAME = "org.apache.hive.jdbc.HiveDriver";

    private HiveTemplate hiveTemplate;

    public abstract <T> T get(String id);

    public abstract <T> T findOne(String hiveSql);

    public abstract <T> T findOne(String hiveSql, Object[] params);

    public abstract <T> List<T> findList(String hiveSql);

    public abstract <T> List<T> findList(String hiveSql, Object[] params);

    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_NAME);
        connection = DriverManager.getConnection(HiveConfig.getImpalaJdbcUrl(), HiveConfig.getHiveUser(), HiveConfig.getHiveUser());
        return connection;
    }

    public static Statement getStatement() throws SQLException, ClassNotFoundException {
        connection = getConn();
        return connection.createStatement();
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        connection = getConn();
        return connection.prepareStatement(sql);
    }

    public void closeConn() throws SQLException {
        if (connection != null) {
            connection.close();
            connection = null;
        }
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }

        if (psmt != null) {
            psmt.close();
            psmt = null;
        }
    }
}
