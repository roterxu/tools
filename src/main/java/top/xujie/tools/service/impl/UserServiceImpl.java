package top.xujie.tools.service.impl;

import top.xujie.tools.entity.User;
import top.xujie.tools.mapper.UserMapper;
import top.xujie.tools.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xujie
 * @since 2020-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
