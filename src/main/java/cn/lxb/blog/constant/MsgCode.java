package cn.lxb.blog.constant;

/**
 * <p>
 * description：MsgBean的code码
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public enum MsgCode {

    // 处理失败
    FAILED(0),
    // 处理成功
    SUCCESS(1);

    private int value;

    private MsgCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
