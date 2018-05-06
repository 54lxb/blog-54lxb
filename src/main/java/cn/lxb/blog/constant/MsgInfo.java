package cn.lxb.blog.constant;

import com.alibaba.fastjson.JSON;

/**
 * <p>
 * description：MsgBean的msg
 * </p>
 *
 * @author 54LXB.
 * @apiNote 知识改变命运，技术改变世界。
 * @since 2017-11-25.
 */
public enum MsgInfo {

    // 处理失败
    FAILED("操作失败！"),
    // 处理成功
    SUCCESS("操作成功！"),
    // 服务器错误
    SERVER_ERROR("服务器错误！"),
    // 未选择文件
    NO_FILE_CHOOSE("请选择要上传的文件！"),
    // 图片格式错误
    IMAGE_TYPE_ERROR("请上传以下格式的图片：" + JSON.toJSONString(FileLimit.IMAGE.getSuffix())),
    // 图片大小错误
    IMAGE_SIZE_ERROR("上传图片大小不能超过：" + JSON.toJSONString(String.format("%sM", FileLimit.IMAGE.getSizeValue() / 1024 / 1024.0 )));

    private String value;

    private MsgInfo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
