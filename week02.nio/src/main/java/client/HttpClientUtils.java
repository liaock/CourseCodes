package client;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * HttpClient configs
 * Created by liaock on 2021/5/16
 **/

@Slf4j
public class HttpClientUtils {

    private static PoolingHttpClientConnectionManager poolConnManager = null;

    /**
     * 它是线程安全的，所有的线程都可以使用它一起发送http请求
     */
    private static CloseableHttpClient httpClient;

    static {
        try {
            System.out.println("初始化HttpClientTest~~~开始");
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTPPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
            // 初始化连接管理器
            poolConnManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            poolConnManager.setMaxTotal(640);// 同时最多连接数
            // 设置最大路由
            poolConnManager.setDefaultMaxPerRoute(320);
            // 此处解释下MaxtTotal和DefaultMaxPerRoute的区别：
            // 1、MaxtTotal是整个池子的大小；
            // 2、DefaultMaxPerRoute是根据连接到的主机对MaxTotal的一个细分；比如：
            // MaxtTotal=400 DefaultMaxPerRoute=200
            // 而我只连接到http://www.abc.com时，到这个主机的并发最多只有200；而不是400；
            // 而我连接到http://www.bac.com 和
            // http://www.ccd.com时，到每个主机的并发最多只有200；即加起来是400（但不能超过400）；所以起作用的设置是DefaultMaxPerRoute
            // 初始化httpClient
            httpClient = getConnection();

            System.out.println("初始化HttpClientTest~~~结束");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static CloseableHttpClient getConnection() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(poolConnManager)
                .setDefaultRequestConfig(config)
                // 设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(2, false)).build();
        return httpClient;
    }


    public static String httpGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpGet);
            String result = EntityUtils.toString(response.getEntity());
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                return result;
            } else {
                log.error("请求{}返回错误码：{},{}", url, code,result);
                return null;
            }
        } catch (IOException e) {
            log.error("http请求异常，{}",url,e);
        } finally {
            try {
                if (response != null)
                    response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
