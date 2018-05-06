package cn.lxb.blog.constant;

/**
 * <p>
 * Description：图片上传类及图片路劲补全型枚举类
 * </P>
 *
 * @author Andy
 * @apiNote 知识改变命运，技术改变世界！
 * @since 2017-09-13 09:00.
 */
public enum ImageTypeEnums {

    BLOG_ARTICLE(1, "/upload/blog/image/"),
    BLOG_ALBUM(2, "/upload/album/image/"),
    BLOG_WORD(3, "/upload/word/image/"),
    BLOG_BLOGGER(4, "/upload/blogger/image/");

    private int index;
    private String imageType;

    ImageTypeEnums(int index, String imageType) {
        this.index = index;
        this.imageType = imageType;
    }

    /* 根据index迭代对应的值 */
    public static String stateOf(int index) {
        for (ImageTypeEnums imageTypeEnums : values()) {
            if (imageTypeEnums.getIndex() == index) {
                return imageTypeEnums.getImageType();
            }
        }
        return null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
