package com.demo.thirdlibrarydemo.eventbus;

/**
 * Created by Administrator on 2017/6/17 0017.
 */

public class EventBean {

    private String msg;

    public EventBean(String msg){
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
