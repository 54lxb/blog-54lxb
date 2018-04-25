package cn.lxb.blog.exception;

/**
 * 自定义异常类
 * Created by Andy on 2017/3/12.
 */
public class BlogException extends Exception {

    private String message;

    public BlogException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
