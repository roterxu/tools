package top.xujie.tools.controller;


import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.xujie.tools.entity.Student;
import top.xujie.tools.service.impl.StudentServiceImpl;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujie
 * @since 2020-01-14
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentServiceImpl studentService;

    @RequestMapping("/getStudent")
    public Object getStudent(Integer id) {
        return studentService.getStudent(id);
    }

    @RequestMapping("/deleteStudent")
    public Object removeStudent(Integer id) {
        studentService.deleteStudent(id);

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "xujie");
        studentService.list(queryWrapper);
        return null;
    }

}
