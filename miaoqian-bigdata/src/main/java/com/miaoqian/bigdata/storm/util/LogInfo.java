package com.miaoqian.bigdata.storm.util;

import java.util.UUID;

/**
 * Created by lind
 * DATETIME 2016/3/29.14:29
 */
public class LogInfo {
    private String rowKey;
    private String logTime;                     //日志记录时间
    private String takeTime;                    //耗时
    private String fromType;                     //来源
    private String serviceName;                 //服务名
    private String jkName;                      //接口名
    private String resultFlag;                  //结果标志
    private String params;                      //参数
    private String result;                      //结果信息
    private String exceptionMsg;                //异常信息

    public LogInfo() {
    }

    public LogInfo(String logTime, String takeTime, String fromType, String serviceName, String jkName, String resultFlag,
                   String params, String result, String exceptionMsg) {
        this.rowKey = UUID.randomUUID().toString();
        this.logTime = logTime;
        this.takeTime = takeTime;
        this.fromType = fromType;
        this.serviceName = serviceName;
        this.jkName = jkName;
        this.resultFlag = resultFlag;
        this.params = params;
        this.result = result;
        this.exceptionMsg = exceptionMsg;
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getJkName() {
        return jkName;
    }

    public void setJkName(String jkName) {
        this.jkName = jkName;
    }

    public String getResultFlag() {
        return resultFlag;
    }

    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
