package com.miaoqian.bigdata.hbase.domain;

import com.miaoqian.bigdata.common.constants.LogContant;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lind
 * DATETIME 2016/11/17.15:44
 */
public class ActionLog {
    private String rowKey;
    private String requestDate;
    private String requestId;
    private String action;
    private String result;
    private String sourcePlatform;
    private String exception;
    private String briefStackTrace;
    private String errorMessage;
    private String traceLogPath;
    private String elapsedTime;
    private String serverIp;
    private String clientIp;
    private String requestUri;
    private String requestType;
    private String httpMethod;
    private String httpStatusCode;
    private String requestContent;
    private String responseContent;
    private String context;
    private Integer currentStep;
    private Integer previousStepId;
    private Integer currentStepId;


    public ActionLog(String rowKey, String requestDate, String requestId, String action, String result, String sourcePlatform, String exception, String briefStackTrace, String errorMessage, String traceLogPath, String elapsedTime, String serverIp, String clientIp, String requestUri, String requestType, String httpMethod, String httpStatusCode, String requestContent, String responseContent, String context, Integer currentStep,
                     Integer previousStepId, Integer currentStepId) {
        this.rowKey = rowKey;
        this.requestDate = requestDate;
        this.requestId = requestId;
        this.action = action;
        this.result = result;
        this.sourcePlatform = sourcePlatform;
        this.exception = exception;
        this.briefStackTrace = briefStackTrace;
        this.errorMessage = errorMessage;
        this.traceLogPath = traceLogPath;
        this.elapsedTime = elapsedTime;
        this.serverIp = serverIp;
        this.clientIp = clientIp;
        this.requestUri = requestUri;
        this.requestType = requestType;
        this.httpMethod = httpMethod;
        this.httpStatusCode = httpStatusCode;
        this.requestContent = requestContent;
        this.responseContent = responseContent;
        this.context = context;
        this.currentStep = currentStep;
        this.previousStepId = previousStepId;
        this.currentStepId = currentStepId;
    }

    public ActionLog(String rowKey, String requestDate, String requestId, String action, String result, String sourcePlatform, String elapsedTime, String serverIp, String requestUri, Integer currentStep,
                     Integer previousStepId, Integer currentStepId) {
        this.rowKey = rowKey;
        this.requestDate = requestDate;
        this.requestId = requestId;
        this.action = action;
        this.result = result;
        this.sourcePlatform = sourcePlatform;
        this.elapsedTime = elapsedTime;
        this.serverIp = serverIp;
        this.requestUri = requestUri;
        this.currentStep = currentStep;
        this.previousStepId = previousStepId;
        this.currentStepId = currentStepId;
    }

    public ActionLog() {
    }

    public ActionLog(String log) throws Exception {
        String[] fileds = StringUtils.split(log, LogContant.LOG_SEPARATOR);
        String[] requestIdFields = StringUtils.split(fileds[3].trim(), LogContant.REQUESTID_SEPARATOR);
        this.rowKey = dealNull(fileds[3]);
        this.requestDate = dealNull(fileds[0]);
        this.sourcePlatform = dealNull(fileds[1]);
        this.result = dealNull(fileds[2]);
        this.requestId = dealNull(requestIdFields[0]);
        this.action = dealNull(fileds[4]);
        this.elapsedTime = dealNull(fileds[5]);
        this.serverIp = dealNull(fileds[6]);
        this.clientIp = dealNull(fileds[7]);
        this.requestUri = dealNull(fileds[8]);
        this.requestType = dealNull(fileds[9]);
        this.httpMethod = dealNull(fileds[10]);
        this.httpStatusCode = dealNull(fileds[11]);
        this.exception = dealNull(fileds[12]);
        this.briefStackTrace = dealNull(fileds[13]);
        this.errorMessage = dealNull(fileds[14]);
        this.traceLogPath = dealNull(fileds[15]);
        this.requestContent = dealNull(fileds[16]);
        this.responseContent = dealNull(fileds[17]);
        if (fileds.length > 18) {
            this.context = dealNull(fileds[18]);
        } else {
            this.context = "null";
        }
        this.currentStep = Integer.parseInt(requestIdFields[1]);
        this.previousStepId = Integer.parseInt(requestIdFields[2]);
        this.currentStepId = Integer.parseInt(requestIdFields[3]);
    }

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public void setRequestUri(String requestUri) {
        this.requestUri = requestUri;
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
        return requestContent;
    }

    public void setRequestContent(String requestContent) {
        this.requestContent = requestContent;
    }

    public String getResponseContent() {
        return responseContent;
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

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public Integer getPreviousStepId() {
        return previousStepId;
    }

    public void setPreviousStepId(Integer previousStepId) {
        this.previousStepId = previousStepId;
    }

    public Integer getCurrentStepId() {
        return currentStepId;
    }

    public void setCurrentStepId(Integer currentStepId) {
        this.currentStepId = currentStepId;
    }

    public String dealNull(String val) {
        if (StringUtils.isEmpty(val)) {
            return "null";
        } else {
            return val.trim();
        }
    }
}
