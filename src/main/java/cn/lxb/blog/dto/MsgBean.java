package cn.lxb.blog.dto;

import cn.lxb.blog.constant.MsgCode;
import cn.lxb.blog.constant.MsgInfo;
import cn.lxb.blog.constant.MsgLevel;

/**
 * <p>
 * Description：消息传输bean
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
public class MsgBean {

    /**
     * 处理状态码
     *
     * @see MsgCode
     */
    private int code;
    /**
     * 消息
     *
     * @see MsgInfo
     */
    private String msg;
    /**
     * 消息等级
     *
     * @see MsgLevel
     */
    private int level;

    /**
     * 返回数据用
     */
    private Object data;

    public MsgBean(MsgCode code, MsgInfo msg, MsgLevel level) {
        this.code = code.getValue();
        this.msg = msg.getValue();
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
