package top.xujie.tools;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import top.xujie.tools.entity.Employee;
import top.xujie.tools.service.impl.EmployeeServiceImpl;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class ToolsApplicationTests {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Test
    public void list(){
        List<Employee> list = employeeService.list(new QueryWrapper<Employee>());
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
