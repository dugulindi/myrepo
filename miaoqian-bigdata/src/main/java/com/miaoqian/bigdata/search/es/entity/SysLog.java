package com.miaoqian.bigdata.search.es.entity;

import com.miaoqian.bigdata.common.constants.LogContant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lind
 * DATETIME 2016/11/4.14:32
 */
@Document(indexName = "logdb", type = "syslog")
public class SysLog {
    @Id
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    String requestId;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    String logLevel;

    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    String moduleName;

    @Field(type = FieldType.Integer, index = FieldIndex.not_analyzed, store = true)
    Integer elapsedTime;

    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true)
    Date logTime;

    @Field(type = FieldType.String, index = FieldIndex.analyzed, store = true)
    String content;

    public SysLog() {
    }

    public SysLog(String log) throws ParseException {
        String[] fileds = StringUtils.split(log, LogContant.LOG_SEPARATOR);
        SimpleDateFormat dateFormat = new SimpleDateFormat(LogContant.LOG_DATE_FORMAT);
        Date logDateTime = dateFormat.parse(fileds[0].trim());
        this.logTime = logDateTime;
        this.moduleName = dealNull(fileds[2]);
        this.logLevel = dealNull(fileds[3]);
        this.requestId = dealNull(fileds[4]);
        this.elapsedTime = StringUtils.isEmpty(fileds[6])?100:Integer.valueOf(fileds[6].trim());
        this.content = log;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Integer getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(Integer elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String dealNull(String val) {
        if (StringUtils.isEmpty(val)) {
            return "null";
        } else {
            return val.trim();
        }
    }
}
