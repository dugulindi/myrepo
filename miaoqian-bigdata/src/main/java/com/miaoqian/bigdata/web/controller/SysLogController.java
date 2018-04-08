package com.miaoqian.bigdata.web.controller;

import com.miaoqian.bigdata.common.constants.ResponseMessage;
import com.miaoqian.bigdata.domain.request.SearchRequest;
import com.miaoqian.bigdata.domain.response.Link;
import com.miaoqian.bigdata.search.es.entity.ActionLog;
import com.miaoqian.bigdata.search.es.entity.SysLog;
import com.miaoqian.bigdata.search.es.service.SysLogService;
import com.miaoqian.bigdata.web.utils.LinkUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by lind
 * DATETIME 2016/11/4.15:02
 */
@Controller
@RequestMapping({"/syslog"})
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object loglist(Model model, Integer pageIndex, Integer pageSize) {
        return sysLogService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public Object logSearch(@RequestBody SearchRequest request) {
        Page<SysLog> sysLogs = sysLogService.logSearch(request.getContent(),
                request.getBeginTime(), request.getEndTime(), Integer.valueOf(request.getPageIndex()), Integer.valueOf(request.getPageSize()));
        return sysLogs;
    }

    @RequestMapping(value = "/search/module/interface")
    @ResponseBody
    public Object logSearchModuleInterface(@RequestBody SearchRequest request) {
        Set<String> moduleInterfaces = sysLogService.logSearchModuleInterface(request.getContent(),
                request.getBeginTime(), request.getEndTime(), Integer.valueOf(request.getPageIndex()), Integer.valueOf(request.getPageSize()));
        return moduleInterfaces;
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public Object count(@RequestBody SearchRequest request) {
        Page<SysLog> activeLogs = sysLogService.count(request.getContent(),
                request.getBeginTime(), request.getEndTime(), Integer.valueOf(request.getPageIndex()), Integer.valueOf(request.getPageSize()));
        return activeLogs.getTotalElements();
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object list(String log) {
        return sysLogService.save(log);
    }

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public Object insert(@PathVariable String id) throws Exception {
        SysLog sysLog = sysLogService.findById(id);
        return sysLog;
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    public String insert(SysLog sysLog) throws Exception {
        sysLogService.insert(sysLog);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/module/list")
    @ResponseBody
    public Object moduleSyslogList(String moduleName,
                            String beginTime,
                            String endTime) {
        return sysLogService.findByModuleAndTime(moduleName,beginTime,endTime);
    }

    @RequestMapping(value = "/link/get")
    @ResponseBody
    public Object tracelinkGet(String requestId) {
        List<SysLog> sysLogs = sysLogService.findByContent(requestId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<Link> linkList = new ArrayList<Link>();
        linkList = LinkUtil.getLinks(sysLogs);
        Link rootLink = LinkUtil.getRootLink(linkList);
        int level = LinkUtil.getLinkLevel(linkList);
        resultMap.put("links", linkList);
        resultMap.put("root", rootLink);
        resultMap.put("level", level);
        return resultMap;
    }

    @RequestMapping(value = "/module/num")
    @ResponseBody
    public Object moduleSyslogNum(String moduleName,
                                   String beginTime,
                                   String endTime) {
        return sysLogService.findByModuleAndTime(moduleName,beginTime,endTime).size();
    }

//    @RequestMapping(value = "/batch/insert")
//    @ResponseBody
//    public Object batchInsert() {
//        for (int i = 0;i<5;i++){
//            String directory = "e:/logdata"+i;
//            File fileDir = new File(directory);
//            if (fileDir.isDirectory()){
//                for (File file : fileDir.listFiles()){
//                    List<String> logs = new ArrayList<String>();
//                    System.out.println(file.getAbsolutePath());
//                    try {
//                        String encoding = "utf-8";
//                        if (file.isFile() && file.exists()) { //判断文件是否存在
//                            InputStreamReader read = new InputStreamReader(
//                                    new FileInputStream(file), encoding);//考虑到编码格式
//                            BufferedReader bufferedReader = new BufferedReader(read);
//                            String lineTxt = null;
//                            while ((lineTxt = bufferedReader.readLine()) != null) {
//                                logs.add(lineTxt);
//                            }
//                            read.close();
//                            sysLogService.save(logs);
//                        } else {
//                            System.out.println("找不到指定的文件");
//                        }
//                    } catch (Exception e) {
//                        System.out.println("读取文件内容出错");
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//        return "SUCCESS";
//    }
//    @RequestMapping(value = "/file/insert")
//    @ResponseBody
//    public Object fileInsert() {
//        List<String> logs = new ArrayList<String>();
//        try {
//            String encoding = "utf-8";
//            File file = new File("e:/test");
//            if (file.isFile() && file.exists()) { //判断文件是否存在
//                InputStreamReader read = new InputStreamReader(
//                        new FileInputStream(file), encoding);//考虑到编码格式
//                BufferedReader bufferedReader = new BufferedReader(read);
//                String lineTxt = null;
//                while ((lineTxt = bufferedReader.readLine()) != null) {
//                    logs.add(lineTxt);
//                }
//                read.close();
//                sysLogService.save(logs);
//            } else {
//                System.out.println("找不到指定的文件");
//            }
//        } catch (Exception e) {
//            System.out.println("读取文件内容出错");
//            e.printStackTrace();
//        }
//        return "SUCCESS";
//    }

}
