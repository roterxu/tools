package top.xujie.tools.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xujie.tools.entity.OrderItem;
import top.xujie.tools.service.OrderItemService;
import top.xujie.tools.service.impl.OrderItemServiceImpl;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujie
 * @since 2019-12-23
 */
@Controller
@RequestMapping("/orderItem")
public class OrderItemController {

    @Resource
    private OrderItemServiceImpl orderItemService;

    //添加用户送货信息
    @RequestMapping("/addOrderItem")
    @ResponseBody
    public Object saveOrder(OrderItem orderItem) {
        orderItemService.save(orderItem);
        return null;
    }
}
