package top.xujie.tools.service;

import top.xujie.tools.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xujie
 * @since 2019-12-24
 */
public interface StaffService extends IService<User> {

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User selectById(Integer id);

    void deleteById(Integer id);
}
