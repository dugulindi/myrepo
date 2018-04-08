package com.miaoqian.bigdata.domain.response;

import java.util.Date;

/**
 * Created by admin on 2017/3/8.
 */
public class Link {
    private String id;
    private String fatherId;
    private Integer time;
    private int level;
    private int step;
    private String ip;
    private String serverIp;
    private String method;
    private Date endTime;
    private String result;
    private String service;

    public Link() {
    }

    public Link(String id, String fatherId, Integer time, int level, int step, String ip, String serverIp, String method
            , Date endTime, String result, String service) {
        this.id = id;
        this.fatherId = fatherId;
        this.time = time;
        this.level = level;
        this.step = step;
        this.ip = ip;
        this.serverIp = serverIp;
        this.method = method;
        this.endTime = endTime;
        this.result = result;
        this.service = service;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getIp() {
        return ip;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
