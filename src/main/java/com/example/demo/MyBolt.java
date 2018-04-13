package com.example.demo;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by lujingzhong on 2018/4/12.
 */
public class MyBolt implements IRichBolt, Serializable {
    private static final Logger log = LoggerFactory.getLogger(MyBolt.class);

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {

    }

    @Override
    public void execute(Tuple tuple) {
        MessageTuple  messageTuple=(MessageTuple) tuple.getValue(0);
        tuple.getMessageId();
        System.out.println("=============>"+ messageTuple.getData()+" "+tuple.getMessageId());



    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
