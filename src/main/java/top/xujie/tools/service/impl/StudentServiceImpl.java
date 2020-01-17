package top.xujie.tools.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.xujie.tools.entity.Student;
import top.xujie.tools.mapper.StudentMapper;
import top.xujie.tools.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xujie
 * @since 2020-01-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Resource
    private RedisTemplate<String, Student> redisTemplate;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Object getStudent(Integer id) {
        ValueOperations<String, Student> operations = redisTemplate.opsForValue();
        String key = "student" + id;
        boolean haskey = redisTemplate.hasKey(key);

        if (haskey) {
            Student student = operations.get(key);
            System.out.println("redis中获得");
            return student;
        }
        Student student = studentMapper.selectById(id);
        operations.set(key, student);

        return student;
    }

    @Override
    public void deleteStudent(Integer id) {
        String key = "student" + id;
        this.removeById(id);
        redisTemplate.delete(key);
    }
}
