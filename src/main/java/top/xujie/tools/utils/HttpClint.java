package top.xujie.tools.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author xujie
 */
public class HttpClint {

/**

*  java调用远程接口
*
*/


    /*
        依赖
     <dependency>
     <groupId>org.apache.httpcomponents</groupId>
     <artifactId>httpclient</artifactId>
     <version>4.3.6</version>
     </dependency>
     <dependency>
     <groupId>org.apache.httpcomponents</groupId>
     <artifactId>httpmime</artifactId>
     <version>4.3.6</version>
     </dependency>
     <dependency>
     <groupId>org.springframework</groupId>
     <artifactId>spring-test</artifactId>
     <version>5.1.10.RELEASE</version>
     <scope>compile</scope>
     </dependency>
      */

		
		

    public static String getCustomerCode(String phoneMd5) {
        String url = "http://192.168.1.65:9977/gamma/api/md52CustomerCode";
        String param = "md5List=" + phoneMd5 + "&code=MqmpZtPg19wC";
        return requestPost(url, param);
    }

    /**
     * @param url   请求的url
     * @param param 请求参数
     * @return 返回值
     */
    public static String requestPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        JSONObject jsonObject = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流（设置请求编码为UTF-8）
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 获取请求返回数据（设置返回数据编码为UTF-8）
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            jsonObject = JSONObject.parseObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return jsonObject.get("data").toString().split(":")[1].substring(1, 10);

    }


    /**
     * 计算文件大小
     * @param size
     * @return
     */
    public static String getPrintSize(String path) {
        File file = new File(path);
        String name = file.getName();
        long size = file.length();


        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            // 因为如果以MB为单位的话，要保留最后1位小数，
            // 因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "." + String.valueOf((size % 100)) + "GB";
        }
    }


}
