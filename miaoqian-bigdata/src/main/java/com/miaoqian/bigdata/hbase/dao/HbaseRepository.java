package com.miaoqian.bigdata.hbase.dao;

import com.miaoqian.bigdata.hbase.domain.ActionLog;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class HbaseRepository {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    private String tableName = "syslog";

    public static byte[] CF = Bytes.toBytes("cf");

    private byte[] id = Bytes.toBytes("id");
    private byte[] requestDate = Bytes.toBytes("request_date");
    private byte[] requestId = Bytes.toBytes("request_id");
    private byte[] currentStep = Bytes.toBytes("current_step");
    private byte[] previousStepId = Bytes.toBytes("previous_step_id");
    private byte[] currentStepId = Bytes.toBytes("current_step_id");
    private byte[] action = Bytes.toBytes("action");
    private byte[] resultByte = Bytes.toBytes("result");
    private byte[] sourcePlatForm = Bytes.toBytes("source_plat_form");
    private byte[] exception = Bytes.toBytes("exception");
    private byte[] briefStackTrace = Bytes.toBytes("brief_stack_trace");
    private byte[] errorMessage = Bytes.toBytes("error_message");
    private byte[] traceLogPath = Bytes.toBytes("trace_log_path");
    private byte[] elapsedTime = Bytes.toBytes("elapsed_time");
    private byte[] serverIp = Bytes.toBytes("server_ip");
    private byte[] clientIp = Bytes.toBytes("client_ip");
    private byte[] requestUri = Bytes.toBytes("request_uri");
    private byte[] requestType = Bytes.toBytes("request_type");
    private byte[] httpMethod = Bytes.toBytes("http_method");
    private byte[] httpStatusCode = Bytes.toBytes("http_status_code");
    private byte[] requestContent = Bytes.toBytes("request_content");
    private byte[] responseContent = Bytes.toBytes("response_content");
    private byte[] context = Bytes.toBytes("context");

    public List<ActionLog> findAll() {
        return hbaseTemplate.find(tableName, new String(CF), new RowMapper<ActionLog>() {
            @Override
            public ActionLog mapRow(Result result, int rowNum) throws Exception {
                return new ActionLog(new String(result.getRow()),
                        Bytes.toString(result.getValue(CF, requestDate)),
                        Bytes.toString(result.getValue(CF, requestId)),
                        Bytes.toString(result.getValue(CF, action)),
                        Bytes.toString(result.getValue(CF, resultByte)),
                        Bytes.toString(result.getValue(CF, sourcePlatForm)),
                        Bytes.toString(result.getValue(CF, exception)),
                        Bytes.toString(result.getValue(CF, briefStackTrace)),
                        Bytes.toString(result.getValue(CF, errorMessage)),
                        Bytes.toString(result.getValue(CF, traceLogPath)),
                        Bytes.toString(result.getValue(CF, elapsedTime)),
                        Bytes.toString(result.getValue(CF, serverIp)),
                        Bytes.toString(result.getValue(CF, clientIp)),
                        Bytes.toString(result.getValue(CF, requestUri)),
                        Bytes.toString(result.getValue(CF, requestType)),
                        Bytes.toString(result.getValue(CF, httpMethod)),
                        Bytes.toString(result.getValue(CF, httpStatusCode)),
                        Bytes.toString(result.getValue(CF, requestContent)),
                        Bytes.toString(result.getValue(CF, responseContent)),
                        Bytes.toString(result.getValue(CF, context)),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, currentStep))),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, previousStepId))),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, currentStepId))));
            }

        });
    }

    public List<ActionLog> findByName(String name) {
        System.out.println(name);
        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes("user"), Bytes.toBytes("user"), CompareFilter.CompareOp.EQUAL, new SubstringComparator(name));
        Scan scan = new Scan();
        scan.setFilter(filter);
        return hbaseTemplate.find(tableName, scan, new RowMapper<ActionLog>() {
            @Override
            public ActionLog mapRow(Result result, int rowNum) throws Exception {
                return new ActionLog(new String(result.getRow()),
                        Bytes.toString(result.getValue(CF, requestDate)),
                        Bytes.toString(result.getValue(CF, requestId)),
                        Bytes.toString(result.getValue(CF, action)),
                        Bytes.toString(result.getValue(CF, resultByte)),
                        Bytes.toString(result.getValue(CF, sourcePlatForm)),
                        Bytes.toString(result.getValue(CF, exception)),
                        Bytes.toString(result.getValue(CF, briefStackTrace)),
                        Bytes.toString(result.getValue(CF, errorMessage)),
                        Bytes.toString(result.getValue(CF, traceLogPath)),
                        Bytes.toString(result.getValue(CF, elapsedTime)),
                        Bytes.toString(result.getValue(CF, serverIp)),
                        Bytes.toString(result.getValue(CF, clientIp)),
                        Bytes.toString(result.getValue(CF, requestUri)),
                        Bytes.toString(result.getValue(CF, requestType)),
                        Bytes.toString(result.getValue(CF, httpMethod)),
                        Bytes.toString(result.getValue(CF, httpStatusCode)),
                        Bytes.toString(result.getValue(CF, requestContent)),
                        Bytes.toString(result.getValue(CF, responseContent)),
                        Bytes.toString(result.getValue(CF, context)),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, currentStep))),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, previousStepId))),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, currentStepId))));
            }
        });

    }

    public ActionLog getByRowKey(String rowKey) {
        return hbaseTemplate.get(tableName, rowKey, new RowMapper<ActionLog>() {
            @Override
            public ActionLog mapRow(Result result, int rowNum) throws Exception {
                return new ActionLog(new String(result.getRow()),
                        Bytes.toString(result.getValue(CF, requestDate)),
                        Bytes.toString(result.getValue(CF, requestId)),
                        Bytes.toString(result.getValue(CF, action)),
                        Bytes.toString(result.getValue(CF, resultByte)),
                        Bytes.toString(result.getValue(CF, sourcePlatForm)),
                        Bytes.toString(result.getValue(CF, exception)),
                        Bytes.toString(result.getValue(CF, briefStackTrace)),
                        Bytes.toString(result.getValue(CF, errorMessage)),
                        Bytes.toString(result.getValue(CF, traceLogPath)),
                        Bytes.toString(result.getValue(CF, elapsedTime)),
                        Bytes.toString(result.getValue(CF, serverIp)),
                        Bytes.toString(result.getValue(CF, clientIp)),
                        Bytes.toString(result.getValue(CF, requestUri)),
                        Bytes.toString(result.getValue(CF, requestType)),
                        Bytes.toString(result.getValue(CF, httpMethod)),
                        Bytes.toString(result.getValue(CF, httpStatusCode)),
                        Bytes.toString(result.getValue(CF, requestContent)),
                        Bytes.toString(result.getValue(CF, responseContent)),
                        Bytes.toString(result.getValue(CF, context)),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, currentStep))),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, previousStepId))),
                        Integer.valueOf(Bytes.toString(result.getValue(CF, currentStepId))));
            }
        });
    }

    public List<ActionLog> getByRowKey(String[] rowKeys) {
        List<ActionLog> logs = new ArrayList<ActionLog>();
        for (String rowKey : rowKeys) {
            logs.add(getByRowKey(rowKey));
        }
        return logs;
    }

    public ActionLog save(final ActionLog actionLog) {
        return hbaseTemplate.execute(tableName, new TableCallback<ActionLog>() {
            public ActionLog doInTable(HTableInterface table) throws Throwable {
                Put p = getPut(actionLog);
                table.put(p);
                return actionLog;
            }
        });
    }

    public void batchSave(final List<ActionLog> actionLogs) {
        hbaseTemplate.execute(tableName, new TableCallback<ActionLog>() {
            public ActionLog doInTable(HTableInterface table) throws Throwable {
                List<Put> putList = new ArrayList<Put>();
                for (ActionLog actionLog : actionLogs) {
                    putList.add(getPut(actionLog));
                }
                if (putList.size() > 0)
                    table.put(putList);
                table.setAutoFlushTo(false);
                return null;
            }
        });
    }

    public Put getPut(ActionLog actionLog) {
        Put p = new Put(Bytes.toBytes(actionLog.getRowKey()));
        p.add(CF, requestDate, Bytes.toBytes(actionLog.getRequestDate()));
        p.add(CF, requestId, Bytes.toBytes(actionLog.getRequestId()));
        p.add(CF, action, Bytes.toBytes(actionLog.getAction()));
        p.add(CF, resultByte, Bytes.toBytes(actionLog.getResult()));
        p.add(CF, sourcePlatForm, Bytes.toBytes(actionLog.getSourcePlatform()));
        p.add(CF, exception, Bytes.toBytes(actionLog.getException()));
        p.add(CF, briefStackTrace, Bytes.toBytes(actionLog.getBriefStackTrace()));
        p.add(CF, errorMessage, Bytes.toBytes(actionLog.getErrorMessage()));
        p.add(CF, traceLogPath, Bytes.toBytes(actionLog.getTraceLogPath()));
        p.add(CF, elapsedTime, Bytes.toBytes(actionLog.getElapsedTime()));
        p.add(CF, serverIp, Bytes.toBytes(actionLog.getServerIp()));
        p.add(CF, clientIp, Bytes.toBytes(actionLog.getClientIp()));
        p.add(CF, requestUri, Bytes.toBytes(actionLog.getRequestUri()));
        p.add(CF, requestType, Bytes.toBytes(actionLog.getRequestType()));
        p.add(CF, httpMethod, Bytes.toBytes(actionLog.getHttpMethod()));
        p.add(CF, httpStatusCode, Bytes.toBytes(actionLog.getHttpStatusCode()));
        p.add(CF, requestContent, Bytes.toBytes(actionLog.getRequestContent()));
        p.add(CF, responseContent, Bytes.toBytes(actionLog.getResponseContent()));
        p.add(CF, context, Bytes.toBytes(actionLog.getContext()));
        p.add(CF, currentStep, Bytes.toBytes(actionLog.getCurrentStep().toString()));
        p.add(CF, previousStepId, Bytes.toBytes(actionLog.getPreviousStepId().toString()));
        p.add(CF, currentStepId, Bytes.toBytes(actionLog.getCurrentStepId().toString()));
        p.setWriteToWAL(false);
        return p;
    }

}
