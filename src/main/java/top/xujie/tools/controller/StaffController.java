package top.xujie.tools.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xujie.tools.entity.User;
import top.xujie.tools.service.StaffService;
import top.xujie.tools.utils.Result;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xujie
 * @since 2019-12-24
 */
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @RequestMapping("/getStaff")
    @ResponseBody
    public Result getUser(Integer id) {
        Result result = new Result();
        User user = staffService.selectById(id);
        result.setData(user);
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result remove(Integer id) {
        Result result = new Result();
        staffService.deleteById(id);
        return result;
    }

}
