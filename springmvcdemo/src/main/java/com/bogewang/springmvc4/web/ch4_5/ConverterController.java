package com.bogewang.springmvc4.web.ch4_5;

import com.bogewang.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bogewang on 2017/7/11.
 */
@Controller
public class ConverterController {
    @RequestMapping(value = "/converter",produces = {"application/x-wisely"})
    public @ResponseBody DemoObj convert(@RequestBody DemoObj demoObj){
        return demoObj;
    }
}
