package top.xujie.tools.utils;

import com.alibaba.fastjson.JSONObject;
import top.xujie.tools.entity.GirlFriend;

import java.util.*;


/**
 * @author xujie
 */
public class Dev {

    public static void main(String[] args) {

        GirlFriend girlFriend = GirlFriend.builder()
                .age(18)
                .name("小丽")
                .height("168cm")
                .weight("48kg")
                .hairColor("浅棕色带点微卷")
                .hobby("逛街,购物,买东西")
                .gift("情人节礼物--阿玛尼红管唇釉")
                .address("告诉你还不给我拐走了")
                .birthday("2002-10-16")
                .phone(" I would not tell you")
                .email("Want to know someone's girlfriend's email?")
                .build();

        System.out.println(girlFriend);

        Random random = new Random();

        for (int i = 0; i <= 10000; i++) {
            Map<String, String> param = new HashMap<>();

            param.put("activityCode", "xujietest");
            param.put("userCode", "jie" + random.nextInt(1000));
            param.put("url", "http://www.baidu.com");
            String doGet = HttpClint.doGet("http://114.55.28.228:8886/shortUrl", param);
            JSONObject jsonObject = JSONObject.parseObject(doGet);
            HttpClint.doGet(jsonObject.get("url").toString(), null);

        }


    }
}
