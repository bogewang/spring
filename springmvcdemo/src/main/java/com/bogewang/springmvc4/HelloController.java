package com.bogewang.springmvc4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bogewang on 2017/7/10.
 */
@Controller     //1 申明这是一个控制器
public class HelloController {
    /*
        @RequestMapping("/index")   //2 配置url和方法之间的映射
        public String hello(){
            return "index";     //3 通过上面viewResolver 的Bean配置, 返回值为index, 说明我们的页面放置的路径为/web-inf/classes/views/index.jsp
        }
    */
}
