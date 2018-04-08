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
 * DATETIME 2016/11/17.15:44
 */
@Document(indexName = "errorlog", type = "actionlog")
public class ActionLog {
    @Field(type = FieldType.Date, index = FieldIndex.not_analyzed, store = true)
    private Date requestDate;
    @Id
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String actionlogId;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String action;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String result;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String sourcePlatform;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String exception;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String briefStackTrace;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String errorMessage;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String traceLogPath;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String elapsedTime;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String serverIP;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String clientIP;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String requestURI;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String requestType;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String httpMethod;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String httpStatusCode;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String requestContent;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String responseContent;
    @Field(type = FieldType.String, index = FieldIndex.not_analyzed, store = true)
    private String context;

    public ActionLog() {
    }

    public ActionLog(String log) throws ParseException {
        String[] fileds = StringUtils.split(log, LogContant.LOG_SEPARATOR);
        String[] requestIdFields = StringUtils.split(fileds[3], LogContant.REQUESTID_SEPARATOR);
        SimpleDateFormat dateFormat = new SimpleDateFormat(LogContant.LOG_DATE_FORMAT);
        Date logDateTime = dateFormat.parse(fileds[0].trim());
        this.requestDate = logDateTime;
        this.sourcePlatform = dealNull(fileds[1]);
        this.result = dealNull(fileds[2]);
        this.actionlogId = dealNull(requestIdFields[0]);
        this.action = dealNull(fileds[4]);
        this.elapsedTime = dealNull(fileds[5]);
        this.serverIP = dealNull(fileds[6]);
        this.clientIP = dealNull(fileds[7]);
        this.requestURI = dealNull(fileds[8]);
        this.requestType = dealNull(fileds[9]);
        this.httpMethod = dealNull(fileds[10]);
        this.httpStatusCode = dealNull(fileds[11]);
        this.exception = dealNull(fileds[12]);
        this.briefStackTrace = dealNull(fileds[13]);
        this.errorMessage = dealNull(fileds[14]);
        this.traceLogPath = dealNull(fileds[15]);
        this.requestContent = dealNull(fileds[16]);
        this.responseContent = dealNull(fileds[17]);
        if (fileds.length > 18)
            this.context = dealNull(fileds[18]);
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getActionlogId() {
        return actionlogId;
    }

    public void setActionlogId(String actionlogId) {
        this.actionlogId = actionlogId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSourcePlatform() {
        return sourcePlatform;
    }

    public void setSourcePlatform(String sourcePlatform) {
        this.sourcePlatform = sourcePlatform;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getBriefStackTrace() {
        return briefStackTrace;
    }

    public void setBriefStackTrace(String briefStackTrace) {
        this.briefStackTrace = briefStackTrace;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getTraceLogPath() {
        return traceLogPath;
    }

    public void setTraceLogPath(String traceLogPath) {
        this.traceLogPath = traceLogPath;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public String getRequestURI() {
        return requestURI;
    }

    public void setRequestURI(String requestURI) {
        this.requestURI = requestURI;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getRequestContent() {
        return requestContent.replace("\"", "").replace(" ", "");
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent.replace("\"", "").replace(" ", "");
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String dealNull(String val) {
        if (StringUtils.isEmpty(val)) {
            return "null";
        } else {
            return val.trim();
        }
    }

}
