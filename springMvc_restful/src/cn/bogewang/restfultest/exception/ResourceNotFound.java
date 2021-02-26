package cn.bogewang.restfultest.exception;

/**
 * Created by bogewang on 2017/6/29.
 */
public class ResourceNotFound extends RuntimeException {
    public ResourceNotFound() {
        super();
    }

    public ResourceNotFound(String message) {
        super(message);
    }

    public ResourceNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
