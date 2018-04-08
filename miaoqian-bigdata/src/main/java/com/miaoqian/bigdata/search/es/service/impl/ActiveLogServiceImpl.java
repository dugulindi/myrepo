package com.miaoqian.bigdata.search.es.service.impl;

import com.miaoqian.bigdata.common.utils.DateUtils;
import com.miaoqian.bigdata.kafka.KafkaService;
import com.miaoqian.bigdata.kafka.config.KafkaConfig;
import com.miaoqian.bigdata.search.es.entity.ActionLog;
import com.miaoqian.bigdata.search.es.entity.ActiveLog;
import com.miaoqian.bigdata.search.es.repository.ActionLogRepository;
import com.miaoqian.bigdata.search.es.repository.ActiveLogRepository;
import com.miaoqian.bigdata.search.es.service.ActiveLogService;
import com.miaoqian.bigdata.search.es.service.ActiveLogService;
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
@Service("activeLogService")
public class ActiveLogServiceImpl implements ActiveLogService {
    private final static Logger logger = LoggerFactory.getLogger(ActiveLogServiceImpl.class);
    @Autowired
    private ActiveLogRepository activeLogRepository;

    @Override
    public int insert(String logString) {
        try {
            ActiveLog actionLog = new ActiveLog(logString);
            activeLogRepository.save(actionLog);
        } catch (ParseException e) {
            logger.info(logString);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 1;
    }

    @Override
    public int insert(ActiveLog activeLog) {
        try {
            activeLogRepository.save(activeLog);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return 1;
    }

    @Override
    public ActiveLog findById(String id) {
        try {
            return activeLogRepository.findOne(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String save(String log) {
        return null;
    }

    @Override
    public String save(List<String> logs) {
        List<ActiveLog> activeLogList = new ArrayList<ActiveLog>();
        for (String log:logs){
            try {
                ActiveLog activeLog = new ActiveLog(log);
                activeLogList.add(activeLog);
            } catch (Exception e) {
                logger.info(e.getMessage());
            }
        }
        try {
            activeLogRepository.save(activeLogList);
        }catch (Exception e) {
            logger.info(e.getMessage());
        }
        return "SUCCESS";
    }


    @Override
    public Page<ActiveLog> findAll(Integer pageIndex, Integer pageSize) {
        try {
            return activeLogRepository.findAll(new PageRequest((pageIndex - 1) * pageSize, pageSize));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Page<ActiveLog> count(String content, String beginTime, String endTime, Integer pageIndex, Integer pageSize) {
        try {
        Date begin = LogParamUtils.getBeginDate(beginTime);
        Date end = LogParamUtils.getEndDate(endTime);
        content = content.trim();
        if (StringUtils.isEmpty(content)){
            return activeLogRepository.findByLogTimeBetween(begin, end,
                    new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
        }else {
            if (content.indexOf("or")>-1){
                return activeLogRepository.findByContentInAndLogTimeBetween(Arrays.asList(content.split(" or ")), begin, end,
                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
            }else {
                return activeLogRepository.findByContentAndLogTimeBetween(content, begin, end,
                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
            }

        }
        } catch (ParseException e) {
            logger.error("date ParseException:date String beginTime {};endTime {}", beginTime, endTime);
            return null;
        }
    }

    @Override
    public List<ActiveLog> findByModuleAndTime(String moduleName, Date begin, Date end) {
        return null;
    }

    @Override
    public List<ActiveLog> findByModuleAndTime(String moduleName, String begin, String end) {
        return null;
    }

    @Override
    public Page<ActiveLog> logSearch(String content,String beginTime, String endTime,
                                     Integer pageIndex, Integer pageSize) {
        pageIndex = (null == pageIndex) ? 1 : pageIndex;
        pageSize = (null == pageSize) ? 10 : pageSize;
        Date begin = null;
        Date end = null;
        try {
            begin = LogParamUtils.getBeginDate(beginTime);
            end = LogParamUtils.getEndDate(endTime);
        } catch (ParseException e) {
            logger.error("date ParseException:date String beginTime {};endTime {}", beginTime, endTime);
        }

        try {
            content = content.trim();
            if (StringUtils.isEmpty(content)){
                return activeLogRepository.findByLogTimeBetween(begin, end,
                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
            }else {
                return activeLogRepository.findByContentAndLogTimeBetween(content, begin, end,
                        new PageRequest(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC, "logTime")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Set<String> logSearchModuleInterface(String content, String beginTime, String endTime, Integer pageIndex, Integer pageSize) {
        return null;
    }
}
