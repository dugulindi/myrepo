package com.miaoqian.bigdata.hive.dao;

import java.io.UnsupportedEncodingException;
import java.sql.*;


/***
 * @author lind
 *         <p/>
 *         2016年5月30日 上午9:23:18
 */
public class HiveJdbcClient {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    private static String hive_jdbc_url = "jdbc:hive2://172.20.4.50:10000/log";
    private static String impala_jdbc_url = "jdbc:hive2://172.20.4.50:21050/log;auth=noSasl";
    private static String user = "";
    private static String password = "";
    private static String sql = "";
    private static ResultSet res;
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            System.setProperty("HADOOP_USER_NAME", "hdfs");
            Class.forName(driverName);
            //Connection conn = DriverManager.getConnection(url, user, password);
            //默认使用端口10000, 使用默认数据库，用户名密码默认
            conn = DriverManager.getConnection(impala_jdbc_url, "hdfs", "123456");
            stmt = conn.createStatement();
//            new HiveJdbcClient().dropTalbe("test");
//            new HiveJdbcClient().loadDataIntoTable("test");
//            new HiveJdbcClient().insertTable();
//            new HiveJdbcClient().showTables();
            new HiveJdbcClient().query("syslog");
            conn.close();
            conn = null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    public void dropTalbe(String tableName) throws SQLException {
        sql = "drop table " + tableName;
        stmt.execute(sql);
    }

    public void createTalbe(String sql) throws SQLException {
        stmt.execute(sql);
    }

    public void showTables() throws SQLException {
        sql = "show tables";
        System.out.println("Running:" + sql);
        res = stmt.executeQuery(sql);
        System.out.println("执行“show tables”运行结果:");
        if (res.next()) {
            System.out.println(res.getString(1));
        }
    }

    public void describeTable(String tableName) throws SQLException {
        sql = "describe " + tableName;
        System.out.println("Running:" + sql);
        res = stmt.executeQuery(sql);
        System.out.println("执行“describe table”运行结果:");
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }
    }

    public void loadDataIntoTable(String tableName) throws SQLException {
        String filepath = "hdfs://master1:8020/user/hive/warehouse/test/hive.txt";
        sql = "load data inpath '" + filepath + "' into table " + tableName;
        System.out.println("Running:" + sql);
        stmt.execute(sql);
    }

    public void query(String tableName) throws SQLException {
        sql = "select * from log." + tableName;
        System.out.println("Running:" + sql);
        res = stmt.executeQuery(sql);
        System.out.println("执行“select * query”运行结果:");
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }
    }

    public void insertTable() throws SQLException, UnsupportedEncodingException {
        String name = new String("令狐冲".getBytes(), "iso8859-1");
        sql = "INSERT INTO test(id,name) VALUES (1, '" + name + "')";
        stmt.execute(sql);
    }

    public void batchInsertTable() throws SQLException, UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder("INSERT INTO test (id,name) VALUES ");
        for (int i = 5; i < 100; i++) {
            if (i == 5) {
                sb.append("(" + i + ", 'lind" + i + "')");
            } else {
                sb.append(",(" + i + ", 'lind" + i + "')");
            }
        }
        stmt.execute(sb.toString());
    }

    public void close() throws SQLException {
        conn.close();
        conn = null;
    }
} 
