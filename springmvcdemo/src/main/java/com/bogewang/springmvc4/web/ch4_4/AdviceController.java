package com.bogewang.springmvc4.web.ch4_4;

import com.bogewang.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by bogewang on 2017/7/11.
 */
@Controller
public class AdviceController {
    @RequestMapping("/advice")
    public String getSomething(@ModelAttribute("msg") String msg, DemoObj obj){ //1
        throw new IllegalArgumentException("非常抱歉,参数有误/" + "来自@ModelAttribute:" + msg);
    }
}
