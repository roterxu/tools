package top.xujie.tools.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import top.xujie.tools.entity.GirlFriend;

import java.util.*;


/**
 * @author xujie
 */
public class Dev {

    public static void main(String[] args) {
        String str = "123,4233,4423,3424,";

        String[] labels = str.split(",");

        List<String> labelList = new ArrayList<>(Arrays.asList(str.split(",")));

        for (String s : labelList) {
            System.out.println(s);
        }
    }
}
