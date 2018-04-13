package com.example.demo;

import java.io.Serializable;

/**
 * Created by lujingzhong on 2018/4/12.
 */
public class MessageTuple implements Serializable {

    private  String msgId; //kafka msg key

    private  String data;


    public MessageTuple(String msgId, String data) {
        this.msgId = msgId;
        this.data = data;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getData() {
        return data;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public void setData(String data) {
        this.data = data;
    }
}
