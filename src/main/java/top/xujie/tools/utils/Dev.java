package top.xujie.tools.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import top.xujie.tools.entity.GirlFriend;

import java.io.IOException;
import java.util.*;


/**
 * @author xujie
 */
public class Dev {

    public static void main(String[] args) throws IOException {
      String str="{\"#尾号#\":\" 8666 \",\"#短链#\":\" https://dx10.cn/LlO \"}";
        System.out.println(str);
        JSONObject jsonObject=JSON.parseObject(str);
        System.out.println(jsonObject.get("#短链#"));
        System.out.println(jsonObject.get("#尾号#"));
    }

    private static Map<String, String> getUrl(String longUrl, String activityCode, List<String> phoneList) throws IOException {
        String url = "http://114.55.28.228:8886/shortUrl";

        HttpClient client = HttpClientBuilder.create().build();
        // GET请求，URL中带请求参数
        HttpPost post = new HttpPost(url);
        Map<String, Object> map = new HashMap<>();
        map.put("longUrl", longUrl);
        map.put("activityCode", activityCode);
        map.put("type", "NORMAL");
        map.put("phoneList", phoneList);

        Gson gson = new Gson();
        String json = gson.toJson(map, new TypeToken<Map<String, Object>>() {
        }.getType());
        post.setEntity(new StringEntity(json, Charsets.UTF_8));
        post.addHeader("Content-Type", "application/json");
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        System.out.println("请求结果");
        System.out.println(JSON.toJSON(result));
        JSONObject jsonObject = JSON.parseObject(result);
        String headUrl = String.valueOf(jsonObject.get("domain"));

        Map<String, String> phoneUrl = new HashMap<>();
        String resultJson = String.valueOf(jsonObject.get("result"));
        System.out.println(resultJson);
        JSONObject jsonObject1 = JSON.parseObject(resultJson);

        for (String phone : phoneList) {
            phoneUrl.put(phone, headUrl + "/" + jsonObject1.get(phone));
        }
        post.releaseConnection();
        return phoneUrl;
    }


}
