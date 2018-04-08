package com.miaoqian.bigdata.domain;

import com.miaoqian.bigdata.common.constants.LogContant;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by lind
 * DATETIME 2016/11/17.15:44
 */
public class ActionLog {
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
    private String serverIP;
    private String clientIP;
    private String requestURI;
    private String requestType;
    private String httpMethod;
    private String httpStatusCode;
    private String requestContent;
    private String responseContent;
    private String context;
    private Integer currentStep;
    private Integer previousStepId;
    private Integer currentStepId;


    public ActionLog(String requestDate, String requestId, String action, String result, String sourcePlatform, String exception, String briefStackTrace, String errorMessage, String traceLogPath, String elapsedTime, String serverIP, String clientIP, String requestURI, String requestType, String httpMethod, String httpStatusCode, String requestContent, String responseContent, String context) {
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
        this.serverIP = serverIP;
        this.clientIP = clientIP;
        this.requestURI = requestURI;
        this.requestType = requestType;
        this.httpMethod = httpMethod;
        this.httpStatusCode = httpStatusCode;
        this.requestContent = requestContent;
        this.responseContent = responseContent;
        this.context = context;
    }

    public ActionLog(String requestDate, String requestId, String action, String result, String sourcePlatform, String elapsedTime, String serverIP, String requestURI) {
        this.requestDate = requestDate;
        this.requestId = requestId;
        this.action = action;
        this.result = result;
        this.sourcePlatform = sourcePlatform;
        this.elapsedTime = elapsedTime;
        this.serverIP = serverIP;
        this.requestURI = requestURI;
    }

    public ActionLog() {
    }

    public ActionLog(String log) {
        String[] fileds = StringUtils.split(log, LogContant.LOG_SEPARATOR);
        String[] requestIdFields = StringUtils.split(fileds[1], LogContant.REQUESTID_SEPARATOR);
        this.requestDate = fileds[0];
        this.requestId = requestIdFields[0];
        this.action = fileds[2];
        this.result = fileds[3];
        this.sourcePlatform = fileds[4];
        this.exception = fileds[5];
        this.briefStackTrace = fileds[6];
        this.errorMessage = fileds[7];
        this.traceLogPath = fileds[8];
        this.elapsedTime = fileds[9];
        this.serverIP = fileds[10];
        this.clientIP = fileds[11];
        this.requestURI = fileds[12];
        this.requestType = fileds[13];
        this.httpMethod = fileds[14];
        this.httpStatusCode = fileds[15];
        this.requestContent = fileds[16];
        this.responseContent = fileds[17];
        this.context = fileds[18];
        this.currentStep = Integer.parseInt(requestIdFields[1]);
        this.previousStepId = Integer.parseInt(requestIdFields[2]);
        this.currentStepId = Integer.parseInt(requestIdFields[3]);
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
}
