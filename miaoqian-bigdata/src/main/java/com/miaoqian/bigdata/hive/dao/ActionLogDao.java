package com.miaoqian.bigdata.hive.dao;


import com.miaoqian.bigdata.common.utils.DateUtils;
import com.miaoqian.bigdata.hbase.domain.ActionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/11/22.13:40
 */
public class ActionLogDao extends HiveDao<ActionLog> {
    private static final Logger logger = LoggerFactory.getLogger(ActionLogDao.class);

    @Override
    public ActionLog get(String id) {
        String hiveSql = "select * from " + defaultDb + "." + defaultTable + " where " + "id=" + id;
        return findOne(hiveSql);
    }

    @Override
    public ActionLog findOne(String hiveSql) {
        ActionLog actionLog = null;
        try {
            stmt = getStatement();
            rs = stmt.executeQuery(hiveSql);
            while (rs.next()) {
                actionLog = new ActionLog();
            }
            closeConn();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return actionLog;
    }

    @Override
    public ActionLog findOne(String hiveSql, Object[] params) {
        ActionLog actionLog = null;
        try {
            psmt = getPreparedStatement(hiveSql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 11, params[i]);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                actionLog = new ActionLog();
            }
            closeConn();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return actionLog;
    }

    @Override
    public List<ActionLog> findList(String hiveSql) {
        List<ActionLog> actionLogs = new ArrayList<ActionLog>();
        try {
            stmt = getStatement();
            rs = stmt.executeQuery(hiveSql);
            while (rs.next()) {
                String longTime = "" + new SimpleDateFormat(DateUtils.getLogDateFormat()).parse(rs.getString("request_date")).getTime();
                ActionLog actionLog = new ActionLog(rs.getString("id"), longTime, rs.getString("request_id"), rs.getString("action"), rs.getString("result"),
                        rs.getString("source_plat_form"), rs.getString("elapsed_time"), rs.getString("server_ip"), rs.getString("request_uri"), Integer.valueOf(rs.getString("current_step")),
                        Integer.valueOf(rs.getString("previous_step_id")), Integer.valueOf(rs.getString("current_step_id")));
                actionLogs.add(actionLog);
            }
            closeConn();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (ParseException e) {
            logger.error(e.getMessage());
        }
        return actionLogs;
    }

    @Override
    public List<ActionLog> findList(String hiveSql, Object[] params) {
        List<ActionLog> actionLogs = new ArrayList<ActionLog>();
        try {
            psmt = getPreparedStatement(hiveSql);
            for (int i = 0; i < params.length; i++) {
                psmt.setObject(i + 11, params[i]);
            }
            rs = psmt.executeQuery();
            while (rs.next()) {
                ActionLog actionLog = new ActionLog();
                actionLogs.add(actionLog);
            }
            closeConn();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        }
        return actionLogs;
    }

    public List<ActionLog> findTraceLink(String requestId) {
        String hiveSql = "select * from " + defaultDb + "." + defaultTable + " where " + "request_id='" + requestId + "' order by current_step asc,previous_step_id asc, current_step_id asc";
        return findList(hiveSql);
    }
}
