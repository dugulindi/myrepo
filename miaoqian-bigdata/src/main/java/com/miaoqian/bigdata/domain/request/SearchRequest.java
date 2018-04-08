package com.miaoqian.bigdata.domain.request;

/**
 * Created by admin on 2017/2/22.
 */
public class SearchRequest {
    String userModules;
    String logLevel;
    String moduleName;
    String content;
    String requestId;
    String params;
    String result;
    String beginTime;
    String endTime;
    Integer pageIndex;
    Integer pageSize;

    public SearchRequest() {
    }

    public SearchRequest(String userModules, String logLevel, String moduleName, String content, String requestId, String params, String result, String beginTime, String endTime, Integer pageIndex, Integer pageSize) {
        this.userModules = userModules;
        this.logLevel = logLevel;
        this.moduleName = moduleName;
        this.content = content;
        this.requestId = requestId;
        this.params = params;
        this.result = result;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public String getUserModules() {
        return userModules;
    }

    public void setUserModules(String userModules) {
        this.userModules = userModules;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
