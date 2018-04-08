package com.miaoqian.bigdata.web.controller;

import com.miaoqian.bigdata.hive.service.HiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HiveController {

    @Autowired
    private HiveService hiveService;

    @RequestMapping(value = "/hive/save")
    @ResponseBody
    public Object save() {
        return hiveService.test();
    }

    @RequestMapping(value = "/trace/link")
    @ResponseBody
    public Object getTraceLink(String requestId) {
        String requestid = requestId.split("-")[0];
        return hiveService.getTraceLink(requestid);
    }
}
