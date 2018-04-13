package com.example.demo;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import com.alibaba.jstorm.client.spout.IAckValueSpout;
import com.alibaba.jstorm.client.spout.IFailValueSpout;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by lujingzhong on 2018/4/12.
 */
public class MySpout implements IRichSpout, IAckValueSpout, IFailValueSpout {

    private SpoutOutputCollector collector;

    private LinkedBlockingDeque<MessageTuple> linkedBlockingDeque = new LinkedBlockingDeque<MessageTuple>(100) ;


    @Override
    public void ack(Object o, List<Object> list) {

    }

    @Override
    public void fail(Object o, List<Object> list) {

    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;


        for (int i = 0; i < 100; i++) {
            MessageTuple messageTuple= new MessageTuple("i"+"","data"+i);
            linkedBlockingDeque.offer(messageTuple);

        }


    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    @Override
    public void nextTuple() {
        MessageTuple messageTuple = null;
        try {
            messageTuple = (MessageTuple)linkedBlockingDeque.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        collector.emit(new Values(messageTuple), messageTuple.getMsgId());
    }

    @Override
    public void ack(Object o) {
        System.out.println("ack=====>"+o);

    }

    @Override
    public void fail(Object o) {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("id"));

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
