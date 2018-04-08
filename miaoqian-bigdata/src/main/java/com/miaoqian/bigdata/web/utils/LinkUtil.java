package com.miaoqian.bigdata.web.utils;

import com.miaoqian.bigdata.domain.response.Link;
import com.miaoqian.bigdata.search.es.entity.SysLog;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2017/3/8.
 */
public class LinkUtil {
    public static List<Link> getLinks(List<SysLog> logList) {
        List<Link> linkList = new ArrayList<Link>();
        for (SysLog sysLog : logList){
            linkList.add(getLink(sysLog));
        }
        return linkList;
    }

    public static Link getLink(SysLog sysLog) {
        Link link = new Link();
        String[] log = sysLog.getContent().split("\\|");
        String request[] = sysLog.getRequestId().split("-");

        link.setId(sysLog.getRequestId());
        link.setIp(log[8].trim());
        link.setServerIp(log[7].trim());
        link.setMethod(log[5].trim());
        link.setLevel(Integer.valueOf(request[1].trim()));
        link.setTime(sysLog.getElapsedTime());
        link.setEndTime(sysLog.getLogTime());
        link.setResult(log[3].trim());
        link.setService(log[2].trim());
        if (link.getLevel()>1){
            link.setFatherId(request[0].trim()+"-"+ formatInteger(link.getLevel()-1)+"-"+formatInteger(link.getLevel()-2)+"-"+request[2]);
        }
        link.setStep(Integer.valueOf(request[3].trim()));
        return link;
    }

    public static Link getRootLink(List<Link> linkList) {
        for (Link link : linkList) {
            if (StringUtils.isEmpty(link.getFatherId())){
                return link;
            }
        }
        return null;
    }

    public static Integer getLinkLevel(List<Link> links) {
        if (null==links||links.size()<1)return 0;
        int level = 1;
        for(Link link : links) {
            if (level<link.getLevel()){
                level = link.getLevel();
            }
        }
        return level;
    }

    public static String formatInteger(Integer i) {
        if (i>=10){
            return i.toString();
        }else {
            return "0"+i;
        }
    }

    public static void main(String[] args) {
        SysLog sysLog = new SysLog();
        sysLog.setContent("2017-03-08-21-53-47-523 | SQ_PROMOTION | SQ_TRANS_DUBBO2 | SUCCESS | d7cb7393731341cc84c7bcdcba123456-03-03-02 | null | 500 | 172.19.11.14 | 111.50.26.20 | /userService/register | HTTP | POST | 200 |  |  |  |  |  | ");
        sysLog.setElapsedTime(500);
        sysLog.setRequestId("d7cb7393731341cc84c7bcdcba123456-03-03-02");
        sysLog.setLogLevel("SUCCESS");
        getLink(sysLog);
    }
}
