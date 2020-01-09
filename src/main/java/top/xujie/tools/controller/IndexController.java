package top.xujie.tools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xujie
 */
@Controller
public class IndexController {

    @RequestMapping("")
    public String index() {
        return "index";
    }

}
