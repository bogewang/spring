package com.bogewang.springmvc4.web.ch4_3;

import com.bogewang.springmvc4.domain.DemoObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bogewang on 2017/7/10.
 */
@RestController     //1 申明是控制器,并且返回数据时不需要@ResponseBody
@RequestMapping("/rest")
public class DemoRestController {
    @RequestMapping(value = "/getjson",produces = {"application/json;charset=utf-8"})   //2 返回json
    public DemoObj getjson(DemoObj obj){
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");  //3 对象会自动转换成json;
    }

    @RequestMapping(value = "/getxml",produces = {"application/xml;charset=utf-8"})     //4 返回xml
    public DemoObj getxml(DemoObj obj){
        return new DemoObj(obj.getId() + 1, obj.getName() + "yy");      //5 自动转换为xml
    }
}
