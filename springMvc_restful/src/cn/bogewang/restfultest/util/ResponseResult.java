package cn.bogewang.restfultest.util;

import java.io.Serializable;

/**
 * Created by bogewang on 2017/6/29.
 */
public class ResponseResult<T> implements Serializable {
    private String code;
    private String desc;
    private T data;

    public ResponseResult() {
    }

    public ResponseResult(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public ResponseResult(String code, T data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
