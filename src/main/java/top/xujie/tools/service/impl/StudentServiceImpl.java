package top.xujie.tools.service.impl;

import top.xujie.tools.entity.Student;
import top.xujie.tools.mapper.StudentMapper;
import top.xujie.tools.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujie
 * @since 2019-12-23
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
