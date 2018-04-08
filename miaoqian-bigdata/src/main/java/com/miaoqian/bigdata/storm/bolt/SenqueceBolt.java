package com.miaoqian.bigdata.storm.bolt;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * Created by lind
 * DATETIME 2016/3/29.9:21
 */
public class SenqueceBolt extends BaseBasicBolt {

    /**
     *
     */
    private static final long serialVersionUID = 6603711776364885400L;

    /* (non-Javadoc)
     * @see backtype.storm.topology.IBasicBolt#execute(backtype.storm.tuple.Tuple, backtype.storm.topology.BasicOutputCollector)
     */
    public void execute(Tuple input, BasicOutputCollector collector) {
        // TODO Auto-generated method stub
        String word = (String) input.getValue(0);
        String out = word.toUpperCase();
        System.out.println("out=" + out);
        collector.emit(new Values(out));
    }

    /* (non-Javadoc)
     * @see backtype.storm.topology.IComponent#declareOutputFields(backtype.storm.topology.OutputFieldsDeclarer)
     */
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("message"));
    }
}
