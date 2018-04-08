package com.miaoqian.bigdata.storm.bolt.logs;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lind
 * DATETIME 2016/3/29.14:58
 */
public class ParseLogInfoBolt extends BaseBasicBolt {
    /**
     *
     */
    private static final long serialVersionUID = -5518838167234208478L;

    private static Logger logger = LoggerFactory.getLogger(ParseLogInfoBolt.class);

    @Override
    public void execute(Tuple tuple, BasicOutputCollector collector) {
//        String log = (String) tuple.getValue(0);
//        LogInfo logInfo = null;
//        try {
//            logInfo = new LogInfo(log);
//        } catch (Exception e) {
//            logger.error("log ParseException:" + log);
//        }
//        collector.emit(new Values(logInfo));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("logdata"));
    }
}
