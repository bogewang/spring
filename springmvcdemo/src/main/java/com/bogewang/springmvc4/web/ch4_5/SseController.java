package com.bogewang.springmvc4.web.ch4_5;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * Created by bogewang on 2017/7/11.
 */
@Controller
public class SseController {
    @RequestMapping(value = "/push",produces = "text/event-stream")     //1
    public @ResponseBody String push(){
        Random random = new Random();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "data:Testing 1,2,3" + random.nextInt() + "\n\n";
    }
}
