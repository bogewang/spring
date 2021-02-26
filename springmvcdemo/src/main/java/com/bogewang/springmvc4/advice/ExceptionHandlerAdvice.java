package com.bogewang.springmvc4.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bogewang on 2017/7/11.
 */
@ControllerAdvice       //1 申明这是一个控制器建言,
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = Exception.class)      //2 value属性可以过滤拦截的条件, 这是拦截所有的Exception;
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error");      //error 页面
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }

    @ModelAttribute     //3 注解键值对添加到全局,所有的注解的@RequestMapping 的方法可获得次键值对;
    public void addAttributes(Model model){
        model.addAttribute("msg","额外信息");       //3
    }


    @InitBinder     //4
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");        //5 此处演示忽略request 参数的id
    }
}
