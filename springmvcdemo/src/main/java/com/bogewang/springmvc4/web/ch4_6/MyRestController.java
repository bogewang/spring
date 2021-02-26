package com.bogewang.springmvc4.web.ch4_6;

import com.bogewang.springmvc4.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bogewang on 2017/7/11.
 */
@RestController
public class MyRestController {
    @Autowired
    DemoService demoService;

    @RequestMapping(value = "/testRest",produces = "text/plain;charset=utf-8")
    public @ResponseBody String testRest(){
        return demoService.saySomething();
    }
}
