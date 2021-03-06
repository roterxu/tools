package top.xujie.tools.service;

import top.xujie.tools.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujie
 * @since 2020-01-14
 */
public interface StudentService extends IService<Student> {

    /**
     * 根据id查询学生
     * @param id
     * @return
     */
    Object getStudent(Integer id);

    /**
     * 删除学生信息
     * @param id
     */
    void deleteStudent(Integer id);
}
