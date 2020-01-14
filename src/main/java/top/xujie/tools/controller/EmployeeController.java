package top.xujie.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xujie.tools.entity.Employee;
import top.xujie.tools.service.impl.EmployeeServiceImpl;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujie
 * @since 2020-01-14
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        List<Employee> list = employeeService.list(new QueryWrapper<Employee>());
        for (Employee employee : list) {
            System.out.println(employee);
        }
        return list;
    }
}
