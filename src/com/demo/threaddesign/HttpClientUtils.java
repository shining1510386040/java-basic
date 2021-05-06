package com.demo.threaddesign;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description http调用工具-依赖apache httpclient（单例模式）
 * @date 2021/4/29 16:29
 * @see
 */
public class HttpClientUtils {


    private Pattern contentDispositionPattern = Pattern.compile(".*filename=\"(.*)\".*");

//    private static final Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

    private static HttpClientUtils instance = new HttpClientUtils();


    private static RequestConfig requestConfig =
            RequestConfig.custom()
                    .setSocketTimeout(15000)
                    // 连接超时时间
                    .setConnectTimeout(15000)
                    // 请求超时时间
                    .setConnectionRequestTimeout(15000)
                    .build();

    private HttpClientUtils() {
    }

    public static HttpClientUtils getInstance() {
        return instance;
    }

    /**
     * @param httpPost
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 发送post请求
     * @date 2021/4/29 17:04
     */
    private static String sendHttpPost(HttpPost httpPost) {
        String responseContent = null;
        httpPost.setConfig(requestConfig);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {

            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return responseContent;
    }

    /**
     * 下载图片
     *
     * @param url
     * @return
     */
    public NetFile downImage(String url) {
        NetFile netFile = new NetFile();
        HttpGet httpGet = new HttpGet(url);
        byte[] responseContent = null;
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            // 解析实体
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toByteArray(entity);
            netFile.setContent(responseContent);
            // 解析下载的文件名
            String filename = null;
            Header contentDisposition = response.getFirstHeader("Content-disposition");
            if (contentDisposition != null && contentDisposition.getValue() != null) {
                String dispostion = contentDisposition.getValue();
                Matcher matcher = contentDispositionPattern.matcher(dispostion);
                if (matcher.find()) {
                    filename = matcher.group(1);
                }
            }
            netFile.setFileName(filename);
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return netFile;
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     */
    public String sendHttpPost(String httpUrl) {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param params  参数(格式:key1=value1&key2=value2)
     */
    public String sendHttpPost(String httpUrl, String params) {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        try {
            //设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求
     *
     * @param httpUrl 地址
     * @param maps    参数
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps) {
        // 创建httpPost
        HttpPost httpPost = new HttpPost(httpUrl);
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 post请求（带文件）
     *
     * @param httpUrl   地址
     * @param maps      参数
     * @param fileLists 附件
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        for (String key : maps.keySet()) {
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        for (File file : fileLists) {
            FileBody fileBody = new FileBody(file);
            meBuilder.addPart("files", fileBody);
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }

    /**
     * 发送 get请求
     *
     * @param httpUrl
     */
    public String sendHttpGet(String httpUrl) {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpGet(httpGet);
    }


    /**
     * 发送Get请求
     *
     * @param httpGet
     * @return
     */
    private String sendHttpGet(HttpGet httpGet) {
        String responseContent = null;
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
//            log.error(e.getMessage());
        }
        return responseContent;
    }

    /**
     * 发送 get请求Https
     *
     * @param httpUrl
     */
    public String sendHttpsGet(String httpUrl) {
        // 创建get请求
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttpsGet(httpGet);
    }

    private String sendHttpsGet(HttpGet httpGet) {
        HttpEntity entity = null;
        String responseContent = null;
        PublicSuffixMatcher publicSuffixMatcher = null;
        try {
            publicSuffixMatcher = PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
        } catch (IOException e) {
//            log.error(e.getMessage());
            return responseContent;
        }
        DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
             CloseableHttpResponse response = httpClient.execute(httpGet)
        ) {
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (ClientProtocolException e) {
//            log.error(e.getMessage());
        } catch (IOException e) {
//            log.error(e.getMessage());
        }
        return responseContent;
    }

    public static class NetFile {
        private byte[] content;
        private String fileName;

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }
    }

}
