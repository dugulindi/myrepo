package com.miaoqian.bigdata.storm.util;

import backtype.storm.spout.Scheme;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by lind
 * DATETIME 2016/3/29.9:18
 */
public class MessageScheme implements Scheme {

    /* (non-Javadoc)
     * @see backtype.storm.spout.Scheme#deserialize(byte[])
     */
    public List<Object> deserialize(byte[] ser) {
        try {
            String msg = new String(ser, "UTF-8");
            return new Values(msg);
        } catch (UnsupportedEncodingException e) {

        }
        return null;
    }


    /* (non-Javadoc)
     * @see backtype.storm.spout.Scheme#getOutputFields()
     */
    public Fields getOutputFields() {
        // TODO Auto-generated method stub
        return new Fields("msg");
    }
}
