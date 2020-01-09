package top.xujie.tools.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import top.xujie.tools.entity.User;
import top.xujie.tools.mapper.StaffMapper;
import top.xujie.tools.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author xujie
 * @since 2019-12-24
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, User> implements StaffService {

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    @Override
    public User selectById(Integer id) {

        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        String key = "user" + id;
        boolean haskey = redisTemplate.hasKey(key);
        if (haskey) {
            User user = operations.get(key);
            System.out.println("redis中获得");
            return user;
        }
        User user = this.getById(id);
        operations.set(key, user);
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        String key = "user" + id;
        this.removeById(id);
        redisTemplate.delete(key);
    }
}
