package top.xujie.tools.utils;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @className: SplitIdsUtil
 * @author: zhonghua.tang
 * @description:  字符串剪切工具类
 * @date: 2019/6/19 14:07
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
public class SplitIdsUtil {


    /**
     * @Author:云淡
     * @Description：将按英文逗号分隔的字符串转化为可做IN查询的字符串
     * @Description：如1,2,3转成‘1’，‘2’，‘3’集合===========【自定义查询调用】
     * @param:ids
     * @return：String
     * @Date：2017/7/9 17:40
     * @since:JDK 1.7
     */
    public static String StrToInStr(String ids) {
        StringBuilder sb = new StringBuilder();
        if (ids.contains(",")) {
            String[] id = ids.split(",");
            for (int i = 0; i < id.length; i++) {
                if (null != id[i]) {
                    sb.append("'").append(id[i]).append("'");
                    if (i + 1 < id.length) {
                        sb.append(",");
                    }
                }
            }
        } else {
            sb.append("'").append(ids).append("'");
        }
        return sb.toString();

    }

    /**
     * @Author:云淡
     * @Description：将用逗号隔开的字符串剪切成List
     * @Description：如1,2,3转成List集合===========【模板查询调用】
     * @param:ids
     * @return：List<String>
     * @Date：2017/7/9 17:40
     * @since:JDK 1.7
     */
    public static List<String> StrToList(String ids) {
        List<String> idList = Lists.newArrayList();
        if (ids.contains(",")) {
            String[] id = ids.split(",");
            for (String oneId : id) {
                if (!Strings.isNullOrEmpty(oneId)) {
                    idList.add(oneId);
                }
            }
        } else {
            if (!Strings.isNullOrEmpty(ids)) {
                idList.add(ids);
            }
        }
        return idList;
    }


    /**
     * @Author:云淡
     * @Description：将用逗号隔开的字符串剪切成List
     * @Description：如1,2,3转成List集合===========【模板查询调用】
     * @param:ids
     * @return：List<String>
     * @Date：2017/7/9 17:40
     * @since:JDK 1.7
     */
    public static List<Integer> StrToList_Int(String ids) {
        List<Integer> idList = Lists.newArrayList();
        if (ids.contains(",")) {
            String[] id = ids.split(",");
            for (String oneId : id) {
                if (!Strings.isNullOrEmpty(oneId)) {
                    idList.add(Integer.parseInt(oneId));
                }
            }
        } else {
            if (!Strings.isNullOrEmpty(ids)) {
                idList.add(Integer.parseInt(ids));
            }
        }
        return idList;
    }


    /**
     * @Author:云淡
     * @Description：将List剪切成逗号隔开的字符串
     * @Description：如List转成集合‘1‘,‘2‘,‘3‘===========【自定义查询组装IN条件时调用】
     * @param:List<String>
     * @return：String
     * @Date：2017/7/9 17:40
     * @since:JDK 1.7
     */
    public static String ListToInStr(List<String> lists) {
        StringBuffer sbf = new StringBuffer("");
        if (lists.size() <= 0) {
            return sbf.toString();
        }

        for (String id : lists) {
            sbf.append("'");
            sbf.append(id);
            sbf.append("'");
            sbf.append(",");
        }
        String returnStr = sbf.substring(0, sbf.toString().length() - 1);
        return returnStr;
    }

    /**
     * @Author:云淡
     * @Description：将List剪切成逗号隔开的字符串
     * @Description：如List转成集合1,2,3===========【返回给前置组装数据时调用】
     * @param:List<String>
     * @return：String
     * @Date：2017/7/9 17:40
     * @since:JDK 1.7
     */
    public static String ListToStr(List<String> lists) {
        StringBuffer sbf = new StringBuffer("");
        if (lists.size() <= 0) {
            return sbf.toString();
        }
        for (String id : lists) {
            sbf.append(id).append(",");
        }
        String returnStr = sbf.substring(0, sbf.toString().length() - 1);
        return returnStr;
    }


    public static void main(String[] args) {
//        List<String> idList=Lists.newArrayList();
//        idList.add("123");
//        idList.add("abc");
//        idList.add("ttt");
//        String mainImg="2";
//        //String itemImgs="2,3";
//         String itemImgs=null;
//       System.out.println( ListToInStr(idList));
        String filename = "img_1530755276893.jpg";
        String imageType = filename.substring(filename.lastIndexOf(".") + 1);
        System.out.println(imageType);
    }
}
