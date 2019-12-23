package top.xujie.tools.service.impl;

import top.xujie.tools.entity.OrderItem;
import top.xujie.tools.mapper.OrderItemMapper;
import top.xujie.tools.service.OrderItemService;
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
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
