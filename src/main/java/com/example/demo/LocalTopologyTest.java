package com.example.demo;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;
import backtype.storm.tuple.Fields;

import java.util.HashMap;

/**
 * Created by lujingzhong on 2018/4/12.
 */
public class LocalTopologyTest {
    

    public static void main(String [] args){

        LocalCluster cluster = new LocalCluster();

        String topoName = "ljz-toponame";

        // Set storm configs
        HashMap<String, Object> stormConf = new HashMap<>();
        //stormConf.put(Config.TOPOLOGY_ACKER_EXECUTORS, 10);
       // stormConf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 5);
        //stormConf.put(Config.TOPOLOGY_WORKERS, 4);



        // Create topology
        TopologyBuilder builder = new TopologyBuilder();

        // spout 个数
        int spout_Parallelism_hint = 4;
        // bolt 个数
        int bolt_Parallelism_hint = 4;

        builder.setSpout(topoName + "_spout", new MySpout(), spout_Parallelism_hint);

        builder.setBolt(topoName + "_bolt", new MyBolt(), bolt_Parallelism_hint)
                .fieldsGrouping(topoName + "_spout", new Fields("id"));

        cluster.submitTopology(topoName, stormConf, builder.createTopology());

        try {
            Thread.sleep(600 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        cluster.killTopology(topoName);
        cluster.shutdown();

        System.exit(0);

    }


}