package top.xujie.tools.service.impl;

import top.xujie.tools.entity.Employee;
import top.xujie.tools.mapper.EmployeeMapper;
import top.xujie.tools.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujie
 * @since 2020-01-14
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
