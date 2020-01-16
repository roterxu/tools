package top.xujie.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.xujie.tools.entity.User;
import top.xujie.tools.utils.Result;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xujie
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public Result index() {
        Result result = new Result();
        List<User> users = new ArrayList<>();
        User user = User.builder().id(1).name("test").startTime(LocalDateTime.now()).build();

        users.add(user);
        result.setData(users);
        return result;
    }

}
