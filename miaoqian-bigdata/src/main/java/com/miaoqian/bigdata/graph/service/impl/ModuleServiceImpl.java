package com.miaoqian.bigdata.graph.service.impl;

import com.google.common.collect.Lists;
import com.miaoqian.bigdata.graph.domain.Interface;
import com.miaoqian.bigdata.graph.domain.Module;
import com.miaoqian.bigdata.graph.repository.InterfaceRepository;
import com.miaoqian.bigdata.graph.repository.ModuleRepository;
import com.miaoqian.bigdata.graph.service.ModuleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lind
 * DATETIME 2016/11/16.14:47
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private InterfaceRepository interfaceRepository;


    @Override
    public Module getModuleByName(String name) {
        return moduleRepository.findByName(name);
    }

    @Override
    public Set<String> findDistinctModule() {
        List<Module> modules = (List<Module>) moduleRepository.findAll();
        Set<String> set = new HashSet<String>();
        for (Module m : modules) {
            set.add(m.getName().trim());
        }
        return set;
    }

    @Override
    public void save(String module1) {
        moduleRepository.save(new Module(module1 ));
    }

    @Override
    public void save(String module1, String module2) {
        save(module1, module2, "");
    }

    @Override
    public void save(String module1, String module2, String interfaces) {
//        System.out.println("module1==================="+module1+", module2======================"+module2+", interfaces===================="+interfaces);
        module1 = module1.trim();
        module2 = module2.trim();
        interfaces = interfaces.trim();
        Module m1 = null;
        Module m2 = null;
        Interface i = null;

        if (StringUtils.isNotEmpty(interfaces)) {
            i = interfaceRepository.findByName(interfaces);
            if (null == i) {
                i = new Interface(interfaces);
            }
        }

        if (StringUtils.isEmpty(module1) && StringUtils.isEmpty(module2)) {//调用服务和被调用服务都为空，直接返回
            return;
        }else if (StringUtils.isEmpty(module1) && StringUtils.isNotEmpty(module2)){ //调用服务为空，
            try {
                m2 = moduleRepository.findByName(module2);
            }catch (Exception e) {
                e.printStackTrace();
            }
            if (null == m2) {
                m2 = new Module(module2);
            }
            //添加接口
            if (i!=null) {
                m2.setInterfaces(addInterface(i, m2.getInterfaces()));
            }
            moduleRepository.save(m2);
        }else if (StringUtils.isNotEmpty(module1) && StringUtils.isEmpty(module2)){ //调用服务不为空，被调用服务为空
            m1 = moduleRepository.findByName(module1);
            if (null == m1) {
                m1 = new Module(module1);
            }
            moduleRepository.save(m1);
        }else{//调用服务和被调用服务都不为空
            m1 = moduleRepository.findByName(module1);
            m2 = moduleRepository.findByName(module2);
            if (null == m1) {
                m1 = new Module(module1);
            }
            if (null == m2) {
                m2 = new Module(module2);
            }
            //添加接口
            if (i!=null) {
                m2.setInterfaces(addInterface(i, m2.getInterfaces()));
            }
            m1.setModules(addModule(m2, m1.getModules()));
            moduleRepository.save(m1);
        }
    }

    @Override
    public Module insert(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public void update(Module module) {
        moduleRepository.save(module);
    }

    @Override
    public void delete(String id) {
        moduleRepository.delete(Long.valueOf(id));
    }

    @Override
    public Module findById(String id) {
        return moduleRepository.findOne(Long.valueOf(id));
    }

    @Override
    public List<Module> findList() {
        return (List<Module>) moduleRepository.findAll();
    }

    @Override
    public void initData() {
        Module module1 = new Module("APP");
        Module module2 = new Module("APP_IOS");
        Module module3 = new Module("APP_ANDROID");
        Module module4 = new Module("BASIS");
        Module module5 = new Module("SQ_PRODUCT");
        Module module6 = new Module("SQ_TRANS");
        Module module7 = new Module("SQ_PROMOTION");
        Module module8 = new Module("SQ_USER");
        Module module9 = new Module("BASIS_JOB");

        module1.setModules(new HashSet<Module>(Lists.newArrayList(module4, module5, module6, module7, module8)));
        module2.setModules(new HashSet<Module>(Lists.newArrayList(module4, module5, module6, module7, module8)));
        module3.setModules(new HashSet<Module>(Lists.newArrayList(module4, module5, module6, module7, module8)));

        moduleRepository.save(Lists.newArrayList(module1, module2, module3, module9));
    }

    public boolean isExistModule(Module module, Set<Module> modules) {
        if (modules.size()<1) {
            return false;
        } else {
            boolean isExist = false;
            for (Module m : modules) {
                if(m.getName().trim().equals(module.getName().trim())){
                    isExist = true;
                    break;
                }
            }
            return  isExist;
        }
    }

    public boolean isExistInterface(Interface interfaces, Set<Interface> interfaceList) {
        if (interfaceList.size()<1) {
            return false;
        } else {
            boolean isExist = false;
            for (Interface i : interfaceList) {
                if(i.getName().trim().equals(interfaces.getName().trim())){
                    isExist = true;
                    break;
                }
            }
            return  isExist;
        }
    }

    public Set<Interface> addInterface(Interface i, Set<Interface> interfaceList) {
        if (null == interfaceList) {
            interfaceList = new HashSet<Interface>();
        }
        if (!isExistInterface(i, interfaceList)) {
            interfaceList.add(i);
        }
        return interfaceList;
    }

    public Set<Module> addModule(Module m, Set<Module> modules) {
        if (null == modules) {
            modules = new HashSet<Module>();
        }
        if (!isExistModule(m, modules)) {
            modules.add(m);
        }
        return modules;
    }

}
