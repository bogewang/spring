package com.bogewang.springmvc4.web.ch4_3;

import com.bogewang.springmvc4.domain.DemoObj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bogewang on 2017/7/10.
 */
@Controller     //1 表明此类是一个控制器
@RequestMapping("/anno")        //2 映射此类的访问路径是 /anno
public class DemoAnnoController {

    @RequestMapping(produces = "text/plain;charset=UTF-8")      //3 默认使用类级别路径 /anno
    public @ResponseBody String index(HttpServletRequest request){      //4 演示可接受httprequest作为参数,
        return "url:" + request.getRequestURI() + "can access";
    }

    @RequestMapping(value = "/pathvar/{str}",produces = "text/plain;charset=utf-8")      //5 接受路径参数
    public @ResponseBody String demoPathVar(@PathVariable String str, HttpServletRequest request){
        return "url:" + request.getRequestURI() + " can access,str:" + str;
    }

    @RequestMapping(value = "/requestParam",produces = "text/plain;charset=utf-8")      //6  常规参数获取
    public @ResponseBody String passRequestParam(Long id, HttpServletRequest request){
        return "url: " + request.getRequestURI() + " can access,id: " + id;
    }


    @RequestMapping(value = "/obj",produces = "application/json;charset=utf-8",method = RequestMethod.POST)     //7 参数到对象
    @ResponseBody     //8 该标签可以用于方法上面
    public  DemoObj passObj(DemoObj demoObj, HttpServletRequest request){
        //return "url:" + request.getRequestURI() + " can access, obj id:" + demoObj.getId() + " ,obj name:" + demoObj.getName();
        return demoObj;
    }

    @RequestMapping(value = {"/name1","/name2"},produces = "text/plain;charset=utf-8")        //9 不同路径可以访问相同的方法;
    public @ResponseBody String remove(HttpServletRequest request){
        return "url: " + request.getRequestURI() + " can access";
    }
}
