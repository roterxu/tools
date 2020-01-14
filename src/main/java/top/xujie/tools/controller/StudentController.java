package top.xujie.tools.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xujie.tools.service.impl.StudentServiceImpl;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujie
 * @since 2020-01-14
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @RequestMapping("/list")
    @ResponseBody
    public Object list() {
        return studentService.list(new QueryWrapper<>());
    }
}
