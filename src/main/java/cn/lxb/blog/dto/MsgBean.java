package cn.lxb.blog.dto;

import cn.lxb.blog.constant.MsgLevel;

/**
 * 消息传输bean
 * Created by 54LXB on 2017-07-11.
 */
public class MsgBean {

    /** code码（在Code.java中定义） */
    private int code = 0;
    /** 消息 */
    private String msg = "操作失败！";
    /** 消息等级，枚举MsgLevel */
    private int level;
    /** 返回数据用 */
    private Object data;

    public MsgBean() {
        super();
    }

    public MsgBean(int code, String msg, MsgLevel level) {
        super();
        this.code = code;
        this.msg = msg;
        if (level != null) {
            this.level = level.getValue();
        }
    }

    public void setMsgBean(int code, String msg, MsgLevel level) {
        this.code = code;
        this.msg = msg;
        this.level = level.getValue();
    }

    public int getCode() {
        return code;
    }

    public MsgBean setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MsgBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public MsgBean setLevel(int level) {
        this.level = level;
        return this;
    }

    public Object getData() {
        return data;
    }

    public MsgBean setData(Object data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return "MsgBean [code=" + code + ", msg=" + msg + ", level=" + level + "]";
    }

}
