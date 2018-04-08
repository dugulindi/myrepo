package com.miaoqian.bigdata.hbase.utils;

/**
 * Created by lind
 * DATETIME 2016/11/17.14:39
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class HbaseUtil {
    private Configuration conf = null;
    HTable table = null;
    private String tableName;


    private static final Logger LOG = LoggerFactory.getLogger(HbaseUtil.class);


//    public HbaseUtil(PropertiesType propertiesType, String tableName) {
//        conf = HBaseConfiguration.create();
//        PropertiesUtil properties = new PropertiesUtil(propertiesType.getValue());
//        conf.set("hbase.zookeeper.quorum", properties.getValue("hbase.zookeeper.quorum"));
//        conf.set("hbase.zookeeper.property.clientPort", properties.getValue("hbase.zookeeper.property.clientPort"));
//
//
//        try {
//            table = new HTable(conf, Bytes.toBytes(tableName));
//        } catch (IOException e) {
//            LOG.error(e.getMessage());
//        }
//
//
//        this.tableName = tableName;
//    }


    /**
     * 添加数据
     *
     * @param rowKey
     * @param column
     * @param value
     */
    public void addData(String rowKey, String column, Object value) {
        try {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.add(Bytes.toBytes(column), Bytes.toBytes(column), Bytes.toBytes(value.toString()));
            table.put(put);
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }


    /**
     * 批量添加数据
     *
     * @param list
     */
    public void addDataBatch(List<Put> list) {
        try {
            table.put(list);
        } catch (RetriesExhaustedWithDetailsException e) {
            LOG.error(e.getMessage());
        } catch (InterruptedIOException e) {
            LOG.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建表
     *
     * @param tableName 表名
     * @param family    列名
     * @throws Exception
     */
    public void creatTable(String tableName, String[] family) throws Exception {
        HBaseAdmin admin = new HBaseAdmin(conf);
        HTableDescriptor desc = new HTableDescriptor(tableName);
        for (int i = 0; i < family.length; i++) {
            HColumnDescriptor columnDesc = new HColumnDescriptor(family[i]);
            desc.addFamily(columnDesc);
        }
        if (admin.tableExists(tableName)) {
            System.out.println("table Exists!");
            System.exit(0);
        } else {
            admin.createTable(desc);
            System.out.println("create table Success!");
        }
    }


    public void dropTable() {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 查询全部
     */
    public void queryAll() {
        Scan scan = new Scan();
        try {
            ResultScanner results = table.getScanner(scan);
            for (Result result : results) {
                int i = 0;
                for (KeyValue rowKV : result.list()) {
                    if (i++ == 0) {
                        System.out.print("rowkey:" + new String(rowKV.getRow()) + " ");
                    }
                    System.out.print(" " + new String(rowKV.getQualifier()) + " ");
                    System.out.print(":" + new String(rowKV.getValue()));
                }


                System.out.println();
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }


    }


    /**
     * 按某字段查询 column = value 的数据
     *
     * @param queryColumn 要查询的列名
     * @param value       过滤条件值
     * @param columns     返回的列名集合
     */
    public ResultScanner queryBySingleColumn(String queryColumn, String value, String[] columns) {
        if (columns == null || queryColumn == null || value == null) {
            return null;
        }


        try {
            SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(queryColumn), Bytes.toBytes(queryColumn), CompareOp.EQUAL, new SubstringComparator(value));
            Scan scan = new Scan();


            for (String columnName : columns) {
                scan.addColumn(Bytes.toBytes(columnName), Bytes.toBytes(columnName));
            }


            scan.setFilter(filter);
            return table.getScanner(scan);
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }


        return null;
    }


    /**
     * 在指定的条件下，按某一字段聚合
     *
     * @param paramMap         参数条件
     * @param dimensionColumns 维度
     * @param aggregateColumn  聚合字段
     * @return 返回map，key 为dimensionColumns 维度相对应的数据，value 为aggregateColumn 字段对应的值
     */
    public Map<String, Long> aggregateBySingleColumn(Map<String, String> paramMap, String[] dimensionColumns, String aggregateColumn) {
        if (dimensionColumns == null || dimensionColumns.length == 0 || paramMap == null || aggregateColumn == null || aggregateColumn.equals("")) {
            return null;
        }


        Map<String, Long> map = null;
        try {
            FilterList filterList = new FilterList();
            Scan scan = new Scan();
            //添加过滤条件
            for (String paramKey : paramMap.keySet()) {
                SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(paramKey), Bytes.toBytes(paramKey), CompareOp.EQUAL, new SubstringComparator(paramMap.get(paramKey)));
                filterList.addFilter(filter);
            }
            scan.setFilter(filterList);


            //要展现的列
            for (String column : dimensionColumns) {
                scan.addColumn(Bytes.toBytes(column), Bytes.toBytes(column));
            }
            scan.addColumn(Bytes.toBytes(aggregateColumn), Bytes.toBytes(aggregateColumn));


            ResultScanner results = table.getScanner(scan);


            //将查询结果放入map 中
            map = new ConcurrentHashMap<String, Long>();
            for (Result result : results) {
//              String dimensionKey = "";
                StringBuilder dimensionKey = new StringBuilder();
                //取值
                String value = new String(result.getValue(Bytes.toBytes(aggregateColumn), Bytes.toBytes(aggregateColumn)));
                Long aggregateValue = value == null ? 0 : Long.parseLong(value);

                //拼接Key
                for (String column : dimensionColumns) {
                    dimensionKey.append("\t" + new String(result.getValue(Bytes.toBytes(column), Bytes.toBytes(column))));
                }
                dimensionKey = dimensionKey.deleteCharAt(0);


                if (map.containsKey(dimensionKey)) {
                    map.put(dimensionKey.toString(), map.get(dimensionKey.toString()) + aggregateValue);
                } else {
                    map.put(dimensionKey.toString(), aggregateValue);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage());
        }


        return map;
    }


//    public static void main(String[] args) throws Exception {
//
//
//        HbaseUtil util = new HbaseUtil(PropertiesType.DDSHOW_HASE, "lft");
////      util.queryAll();
//        Map<String, String> paramMap = new HashMap<String, String>();
//        paramMap.put("stat_date", "2016-02-03");
//        Map<String, Long> map = util.aggregateBySingleColumn(paramMap, new String[]{"date", "name"}, "pv");
//
//
//        for (String key : map.keySet()) {
//            System.out.println(key + "\t" + map.get(key));
//        }
//
//    }
}
