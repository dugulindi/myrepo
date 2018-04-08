package com.miaoqian.bigdata.search.es.service.impl;

import com.miaoqian.bigdata.common.utils.DateUtils;
import com.miaoqian.bigdata.kafka.KafkaService;
import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import com.miaoqian.bigdata.search.es.entity.ActionLog;
import com.miaoqian.bigdata.search.es.entity.SysLog;
import com.miaoqian.bigdata.search.es.repository.ActionLogRepository;
import com.miaoqian.bigdata.search.es.repository.SysLogRepository;
import com.miaoqian.bigdata.search.es.service.SysLogService;
import com.miaoqian.bigdata.search.util.LogParamUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

/**
 * Created by lind
 * DATETIME 2016/11/4.14:57
 */
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {
    private final static Logger logger = LoggerFactory.getLogger(SysLogServiceImpl.class);
    @Autowired
    private SysLogRepository sysLogRepository;
    @Autowired
    private ActionLogRepository actionLogRepository;
    @Autowired
    private KafkaService kafkaService;

    @Override
    public int insert(String logString) {
        try {
            SysLog actionLog = new SysLog(logString);
            sysLogRepository.save(actionLog);
        } catch (ParseException e) {
            logger.info(logString);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 1;
    }

    @Override
    public int insert(SysLog sysLog) {
        try {
            sysLogRepository.save(sysLog);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 1;
    }

    @Override
    public SysLog findById(String id) {
        try {
            return sysLogRepository.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String save(String log) {
        try {
            String[] logs = log.split("\\| \n,");
            for (String templog : logs) {
                SysLog sysLog = new SysLog(templog);
                sysLogRepository.save(sysLog);
                if ("ERROR".equalsIgnoreCase(sysLog.getLogLevel())) {
                    ActionLog actionLog = new ActionLog(templog);
                    actionLogRepository.save(actionLog);
                }
            }
        } catch (Exception e) {
            logger.info(e.getMessage());
            kafkaService.sendMessage(KafkaConfig.getTopic(),log);
        }
        return null;
    }

    @Override
    public String save(List<String> logs) {
        List<SysLog> syslogList = new ArrayList<SysLog>();
        List<ActionLog> actionLogs = new ArrayList<ActionLog>();
        for (String log:logs){
            try {
                SysLog sysLog = new SysLog(log);
                syslogList.add(sysLog);

                if ("ERROR".equalsIgnoreCase(sysLog.getLogLevel())) {
                    ActionLog actionLog = new ActionLog(log);
                    actionLogs.add(actionLog);
                }
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        try {
            sysLogRepository.save(syslogList);
            if (actionLogs.size()>0){
                actionLogRepository.save(actionLogs);
            }
        }catch (Exception e) {
            logger.info(e.getMessage());
            for (String log:logs){
                kafkaService.sendMessage(KafkaConfig.getTopic(),log);
            }
        }
        return null;
    }

    @Override
    public Page<SysLog> findAll(Integer pageIndex, Integer pageSize) {
        try {
            return sysLogRepository.findAll(new PageRequest((pageIndex - 1) * pageSize, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<SysLog> findByContent(String content) {
        return sysLogRepository.findByContentContaining(content);
    }

    @Override
    public List<SysLog> findByModuleAndTime(String moduleName, Date begin, Date end) {
        return sysLogRepository.findByModuleNameAndLogLevelAndLogTimeBetween(moduleName, "ERROR", begin, end);
    }

    @Override
    public List<SysLog> findByModuleAndTime(String moduleName, String begin, String end) {
        return new ArrayList<SysLog>();
    }

    @Override
    public Page<SysLog> count(String content, String beginTime, String endTime, Integer pageIndex, Integer pageSize) {
        try {
            Date begin = LogParamUtils.getBeginDate(beginTime);
            Date end = LogParamUtils.getEndDate(endTime);
            content = content.trim();
            if (StringUtils.isEmpty(content)){
                return sysLogRepository.findByLogTimeBetween(begin, end,
                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
            }else {
                if (content.indexOf("or")>-1){
                    return sysLogRepository.findByContentInAndLogTimeBetween(Arrays.asList(content.split(" or ")), begin, end,
                            new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
                }else {
                    return sysLogRepository.findByContentAndLogTimeBetween(content, begin, end,
                            new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
                }

            }
        } catch (ParseException e) {
            logger.error("date ParseException:date String beginTime {};endTime {}", beginTime, endTime);
            return null;
        }
    }

    @Override
    public Page<SysLog> logSearch(String content, String beginTime, String endTime,
                                  Integer pageIndex, Integer pageSize) {
        pageIndex = (null == pageIndex) ? 1 : pageIndex;
        pageSize = (null == pageSize) ? 100 : pageSize;
        try {
            Date begin = LogParamUtils.getBeginDate(beginTime);
            Date end = LogParamUtils.getEndDate(endTime);
            content = content.trim();
            if (StringUtils.isEmpty(content)){
                return sysLogRepository.findByLogTimeBetween(begin, end,
                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
            }else {
                if (content.indexOf("or")>-1){
                    return sysLogRepository.findByContentInAndLogTimeBetween(Arrays.asList(content.split(" or ")), begin, end,
                            new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
                }else {
                    return sysLogRepository.findByContentAndLogTimeBetween(content, begin, end,
                            new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
                }

            }
        } catch (ParseException e) {
            logger.error("date ParseException:date String beginTime {};endTime {}", beginTime, endTime);
            return null;
        }
    }

    @Override
    public Set<String> logSearchModuleInterface(String content, String beginTime, String endTime, Integer pageIndex, Integer pageSize) {
        Set<String> moduleInterfaces = new HashSet<String>();
        Page<SysLog> logs = logSearch(content, beginTime, endTime, pageIndex, pageSize);
        List<SysLog> logList = new ArrayList<SysLog>();

        int totalPage = logs.getTotalPages();
//        if (totalPage>totalPage) {
//            totalPage = 100;
//        }
        System.out.println(totalPage);
        for (int i = 1; i<=totalPage; i++) {
            System.out.println(i);
            if (i==1){
                logList = logs.getContent();
            }else{
                logList = logSearch(content, beginTime, endTime, i, pageSize).getContent();
            }
            for (SysLog sysLog : logList) {
                String log = sysLog.getContent();
                moduleInterfaces.add(log.split("\\|")[5].trim());
            }
        }
        return moduleInterfaces;
    }
//    @Override
//    public Page<SysLog> logSearch(String userModules, String logLevel, String moduleName, String requestId, String
//            params, String result, String beginTime, String endTime, Integer pageIndex, Integer pageSize) {
//        pageIndex = (null == pageIndex) ? 1 : pageIndex;
//        pageSize = (null == pageSize) ? 10 : pageSize;
//        Date begin = null;
//        Date end = null;
//        try {
//            begin = LogParamUtils.getBeginDate(beginTime);
//            end = LogParamUtils.getEndDate(endTime);
//        } catch (ParseException e) {
//            logger.error("date ParseException:date String beginTime {};endTime {}", beginTime, endTime);
//        }
//        Collection<String> levels = LogParamUtils.getLogLevels(logLevel);
//        Collection<String> modules = LogParamUtils.getModules(userModules, moduleName);
//
//        try {
//            if (StringUtils.isEmpty(requestId) && StringUtils.isEmpty(params) && StringUtils.isEmpty(result)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndLogTimeBetween(levels, modules, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else if (StringUtils.isEmpty(requestId) && StringUtils.isEmpty(params)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndResultContainingAndLogTimeBetween(levels, modules, result, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else if (StringUtils.isEmpty(params) && StringUtils.isEmpty(result)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndRequestIdAndLogTimeBetween(levels, modules, requestId, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else if (StringUtils.isEmpty(requestId) && StringUtils.isEmpty(result)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndParamsContainingAndLogTimeBetween(levels, modules, params, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else if (StringUtils.isEmpty(requestId)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndParamsContainingAndResultContainingAndLogTimeBetween(levels, modules, params, result, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else if (StringUtils.isEmpty(params)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndRequestIdAndResultContainingAndLogTimeBetween(levels, modules, requestId, result, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else if (StringUtils.isEmpty(result)) {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndRequestIdAndParamsContainingAndLogTimeBetween(levels, modules, requestId, params, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            } else {
//                return sysLogRepository.findByLogLevelInAndModuleNameInAndRequestIdAndParamsContainingAndResultContainingAndLogTimeBetween(levels, modules, requestId, params, result, begin, end,
//                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
