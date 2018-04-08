package com.miaoqian.bigdata.web.controller;

import com.miaoqian.bigdata.common.constants.ResponseMessage;
import com.miaoqian.bigdata.graph.domain.PersonGraph;
import com.miaoqian.bigdata.graph.domain.User;
import com.miaoqian.bigdata.graph.service.PersonGraphService;
import com.miaoqian.bigdata.graph.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lind
 * DATETIME 2016/11/15.17:15
 */

@Controller
@RequestMapping({"/graph/person"})
public class PersonGraphController {
    @Autowired
    PersonGraphService personGraphService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create")
    @ResponseBody
    public String insert(PersonGraph personGraph) throws Exception {
        personGraphService.insert(personGraph);
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(String userName) throws Exception {
        return userService.update(userName);
    }

    @RequestMapping(value = "/update_area")
    @ResponseBody
    public Object updateArea(String userName) throws Exception {
        return userService.updateAreas(userName);
    }

    @RequestMapping(value = "/find")
    @ResponseBody
    public Object findByName(String name) throws Exception {
        return personGraphService.findByName(name);
    }

    @RequestMapping(value = "/init")
    @ResponseBody
    public Object initData() {
        userService.initData();
        return ResponseMessage.SUCCESS;
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public User initData(String name) {
        return userService.getUserByName(name);
    }

}
