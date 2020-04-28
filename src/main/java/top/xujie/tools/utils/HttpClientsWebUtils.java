package top.xujie.tools.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenpeng
 */
public class HttpClientsWebUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpClientsWebUtils.class);
    private static final RequestConfig REQUEST_CONFIG = RequestConfig.custom().setSocketTimeout(300000).setConnectTimeout(300000).build();

    private static final RequestConfig REQUEST_CONFIG_4S = RequestConfig.custom().setSocketTimeout(3500).setConnectTimeout(3500).build();

    public static String doPostJson(String url, String jsonData) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity entity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        entity.setContentType("application/json");

        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        httpPost.setConfig(REQUEST_CONFIG);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK) {
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), Consts.UTF_8);
            }
        } finally {
            httpResp.close();
            httpclient.close();
        }
        return result;
    }

    public static String doPostJson(String url, String jsonData, Map<String, String> headers) throws Exception {
        return doPostJson(url, jsonData, headers, REQUEST_CONFIG);
    }

    /**
     * 4s内返回
     */
    public static String doPostJsonIn4s(String url, String jsonData, Map<String, String> headers) throws Exception {
        return doPostJson(url, jsonData, headers, REQUEST_CONFIG_4S);
    }

    public static String doPostJson(String url, String jsonData, Map<String, String> headers, RequestConfig requestConfig) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (headers != null) {
            for (HashMap.Entry<String, String> entry : headers.entrySet()) {
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        StringEntity entity = new StringEntity(jsonData, ContentType.APPLICATION_JSON);
        entity.setContentType("application/json");

        httpPost.setEntity(entity);
        //设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK) {
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), Consts.UTF_8);
            }
        } finally {
            httpResp.close();
            httpclient.close();
        }
        return result;
    }

    public static String doPost(String url, Map<String, String> paramsMap) throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        for (String key : paramsMap.keySet()) {
            params.add(new BasicNameValuePair(key, paramsMap.get(key)));
        }
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params, Consts.UTF_8));
        //设置请求和传输超时时间
        httpPost.setConfig(REQUEST_CONFIG);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK) {
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), Consts.UTF_8);
            }
        } finally {
            httpResp.close();
            httpclient.close();
        }
        return result;
    }

    public static String doGet(String url, Map<String, String> paramsMap) throws Exception {
        List<NameValuePair> params = new ArrayList<>();
        if (paramsMap != null) {
            for (String key : paramsMap.keySet()) {
                params.add(new BasicNameValuePair(key, paramsMap.get(key)));
            }
        }
        String param = EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
        if (StringUtils.isNotBlank(param)) {
            url = url + "?" + param;
        }
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:50.0) Gecko/20100101 Firefox/50.0");
        //设置请求和传输超时时间
        httpGet.setConfig(REQUEST_CONFIG);
        CloseableHttpResponse httpResp = httpclient.execute(httpGet);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK) {
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), Consts.UTF_8);
            }
        } finally {
            httpResp.close();
            httpclient.close();
        }
        return result;
    }
}
