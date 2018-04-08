package com.miaoqian.bigdata.web.controller;

import com.miaoqian.bigdata.common.constants.ResponseMessage;
import com.miaoqian.bigdata.domain.request.SearchRequest;
import com.miaoqian.bigdata.search.es.entity.ActiveLog;
import com.miaoqian.bigdata.search.es.service.ActiveLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/4.15:02
 */
@Controller
@RequestMapping({"/activelog"})
public class ActiveLogController {

    @Autowired
    private ActiveLogService activeLogService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object loglist(Model model, Integer pageIndex, Integer pageSize) {
        return activeLogService.findAll(pageIndex, pageSize);
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public Object logSearch(@RequestBody SearchRequest request) {
        Page<ActiveLog> activeLogs = activeLogService.logSearch(request.getContent(),
                request.getBeginTime(), request.getEndTime(), Integer.valueOf(request.getPageIndex()), Integer.valueOf(request.getPageSize()));
        return activeLogs;
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public Object count(@RequestBody SearchRequest request) {
        Page<ActiveLog> activeLogs = activeLogService.count(request.getContent(),
                request.getBeginTime(), request.getEndTime(), Integer.valueOf(request.getPageIndex()), Integer.valueOf(request.getPageSize()));
        return activeLogs.getTotalElements();
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object list(String log) {
        return activeLogService.save(log);
    }

    @RequestMapping(value = "/batch/insert")
    @ResponseBody
    public Object batchInsert() {
        for (int i = 0;i<5;i++){
            String directory = "e:/logdata"+i;
            File fileDir = new File(directory);
            if (fileDir.isDirectory()){
                for (File file : fileDir.listFiles()){
                    List<String> logs = new ArrayList<String>();
                    System.out.println(file.getAbsolutePath());
                    try {
                        String encoding = "utf-8";
                        if (file.isFile() && file.exists()) { //判断文件是否存在
                            InputStreamReader read = new InputStreamReader(
                                    new FileInputStream(file), encoding);//考虑到编码格式
                            BufferedReader bufferedReader = new BufferedReader(read);
                            String lineTxt = null;
                            while ((lineTxt = bufferedReader.readLine()) != null) {
                                logs.add(lineTxt);
                            }
                            read.close();
                            activeLogService.save(logs);
                        } else {
                            System.out.println("找不到指定的文件");
                        }
                    } catch (Exception e) {
                        System.out.println("读取文件内容出错");
                        e.printStackTrace();
                    }
                }
            }
        }
        return "SUCCESS";
    }

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public Object insert(@PathVariable String id) throws Exception {
        ActiveLog activeLog = activeLogService.findById(id);
        return activeLog;
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    public String insert(ActiveLog activeLog) throws Exception {
        activeLogService.insert(activeLog);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/module/list")
    @ResponseBody
    public Object moduleactiveLogList(String moduleName,
                            String beginTime,
                            String endTime) {
        return activeLogService.findByModuleAndTime(moduleName,beginTime,endTime);
    }

//    @RequestMapping(value = "/module/actionlog/list")
//    @ResponseBody
//    public Object moduleActionlogList(String moduleName,
//                                   String beginTime,
//                                   String endTime) {
//        return actionLogService.findByModuleAndTime(moduleName,beginTime,endTime);
//    }


}
