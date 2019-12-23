package top.xujie.tools;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import top.xujie.tools.entity.Student;
import top.xujie.tools.service.StudentService;
import top.xujie.tools.service.impl.StudentServiceImpl;

import java.util.List;

@SpringBootTest
class ToolsApplicationTests {

    @Autowired
    private StudentServiceImpl studentService;

    @Test
    void contextLoads() {
        List<Student> list = studentService.list();
        System.out.println(list);
    }

}
