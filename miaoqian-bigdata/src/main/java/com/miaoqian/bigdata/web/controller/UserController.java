package com.miaoqian.bigdata.web.controller;

import com.miaoqian.bigdata.common.constants.ResponseMessage;
import com.miaoqian.bigdata.hbase.domain.ActionLog;
import com.miaoqian.bigdata.hbase.service.HbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lind
 * DATETIME 2016/11/10.14:59
 */
@Controller
@RequestMapping({"/user"})
public class UserController {
    @Autowired
    HbaseService hbaseService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(Model model, Integer pageIndex, Integer pageSize) {
        return hbaseService.findList();
    }

    @RequestMapping(value = "/get/{id}")
    @ResponseBody
    public Object insert(@PathVariable String id) throws Exception {
        ActionLog actionLog = hbaseService.findById(id);
        return actionLog;
    }

    @RequestMapping(value = "/create")
    @ResponseBody
    public String insert(ActionLog actionLog) throws Exception {
        hbaseService.insert(actionLog);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(ActionLog actionLog) throws Exception {
        hbaseService.update(actionLog);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable String id) throws Exception {
        hbaseService.delete(id);
        return ResponseMessage.SUCCESS;
    }
}
