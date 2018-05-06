package cn.lxb.blog.constant;

/**
 * 消息等级
 * Created by 54LXB on 2017-07-11.
 */
public enum MsgLevel {
    // 普通消息
    NORMAL(1),
    // 警告消息，业务继续
    WARNING(2),
    // 错误消息，业务失败
    ERROR(3);

    private int value;

    private MsgLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
