package com.bogewang.springmvc4.messageconverter;

import com.bogewang.springmvc4.domain.DemoObj;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 集成接口来实现自定义的HttpMessageConverter;
 * Created by bogewang on 2017/7/11.
 */
public class MyMessageConverter extends AbstractHttpMessageConverter<DemoObj> {
    /**
     * 4 表明本HttpMessageConverter只处理DemoObj这个类;
     * @param clazz
     * @return
     */
    @Override
    protected boolean supports(Class<?> clazz) {
        return false;
    }

    /**
     * 3 重写readIntenal 方法,处理请求的数据.代码表明我们处理由"-" 隔开的数据,并转化成DemoObj对象;
     * @param clazz
     * @param inputMessage
     * @return
     * @throws IOException
     * @throws HttpMessageNotReadableException
     */
    @Override
    protected DemoObj readInternal(Class<? extends DemoObj> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        String temp = StreamUtils.copyToString(inputMessage.getBody(), StandardCharsets.UTF_8);
        String[] tempArr = temp.split("-");
        return new DemoObj(new Long(tempArr[0]),tempArr[1]);
    }

    /**
     * 5 重写writeInternal,处理如何输出数据到response. 此列中,我们在原样输出前面加上Hello;
     * @param demoObj
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(DemoObj demoObj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String out = "hello:" + demoObj.getId() + "-" + demoObj.getName();
        outputMessage.getBody().write(out.getBytes());
    }

    public MyMessageConverter() {
        super(new MediaType("application","x-wisely", StandardCharsets.UTF_8));       //2 新建一个我们自定义的媒体类型application/x-wisely;
    }

}
