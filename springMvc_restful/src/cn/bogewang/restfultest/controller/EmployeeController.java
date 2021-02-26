package cn.bogewang.restfultest.controller;

import cn.bogewang.restfultest.entity.Employee;
import cn.bogewang.restfultest.exception.ResourceNotFound;
import cn.bogewang.restfultest.service.EmployeeService;
import cn.bogewang.restfultest.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bogewang on 2017/6/29.
 */
@RestController
@RequestMapping("/employs")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/list", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public List<Employee> employs(Integer offset, Integer limit){
        offset = offset == null ? 0 :offset;
        limit = limit == null ? 20 :limit;
        return employeeService.queryEmployeeList(offset,limit);
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST,produces = "application/json,charset=UTF-8")
    public int saveEmployee(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @RequestMapping(value = "/id",method = RequestMethod.GET,produces = "application/json,charset=UTF-8")
    public ResponseResult<Employee> employeeById(@PathVariable long id){
        ResponseResult<Employee> result = new ResponseResult<>();
        Employee employee = employeeService.selectById(id);
        if (employee != null){
            throw new ResourceNotFound(String .valueOf(id));
        }

        result.setCode(String.valueOf(HttpStatus.OK));
        result.setData(employee);
        return result;
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseResult<Object> handlerException(ResourceNotFound e){

        ResponseResult<Object> result = new ResponseResult<>();
        result.setCode(String.valueOf(HttpStatus.NOT_FOUND));
        result.setDesc(e.getMessage());
        return result;
    }
}
