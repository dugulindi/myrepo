package com.miaoqian.bigdata.web.controller;

import com.miaoqian.bigdata.common.constants.ResponseMessage;
import com.miaoqian.bigdata.graph.domain.Module;
import com.miaoqian.bigdata.graph.service.InterfaceService;
import com.miaoqian.bigdata.graph.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lind
 * DATETIME 2016/11/15.17:15
 */

@Controller
@RequestMapping({"/graph/module"})
public class ModuleController {
    @Autowired
    ModuleService moduleService;
    @Autowired
    InterfaceService interfaceService;

    @RequestMapping(value = "/save")
    @ResponseBody
    public String insert(String module1, String module2) throws Exception {
        moduleService.save(module1, module2);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/save1")
    @ResponseBody
    public String insert1(String log) throws Exception {
        String[] logs = log.split("\\| \n,");
        for (String syslog : logs) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("module1", syslog.split("\\|")[1].trim());
            params.put("module2", syslog.split("\\|")[2].trim());
            moduleService.save(syslog.split("\\|")[1].trim(), syslog.split("\\|")[2].trim(), syslog.split("\\|")[5].trim());
        }
        return ResponseMessage.SUCCESS;
    }


    @RequestMapping(value = "/create")
    @ResponseBody
    public String insert(Module module) throws Exception {
        moduleService.insert(module);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/find")
    @ResponseBody
    public Object findByName(String name) throws Exception {
        return moduleService.getModuleByName(name);
    }

    @RequestMapping(value = "/interfaces/find")
    @ResponseBody
    public Object findInterfaceByName(String name) throws Exception {
        return interfaceService.findByName(name);
    }

    @RequestMapping(value = "/interfaces")
    @ResponseBody
    public Object findInterfaceByModuleName(String moduleName) throws Exception {
        return interfaceService.findByModuleName(moduleName);
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list() {
        return moduleService.findList();
    }

    @RequestMapping(value = "/distinct/list")
    @ResponseBody
    public Object distinctList() {
        return moduleService.findDistinctModule();
    }

    @RequestMapping(value = "/init")
    @ResponseBody
    public Object initData() {
        moduleService.initData();
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/module/add")
    @ResponseBody
    public Object moduleAdd(String name) {
        moduleService.save(name);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/interface/add")
    @ResponseBody
    public Object interfaceAdd(String name) {
        interfaceService.save(name);
        return ResponseMessage.SUCCESS;
    }

}
