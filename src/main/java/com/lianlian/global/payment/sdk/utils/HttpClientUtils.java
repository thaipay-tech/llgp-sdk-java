package com.lianlian.global.payment.sdk.utils;

import com.alibaba.fastjson.JSON;
import com.lianlian.global.payment.sdk.support.GlobalConst;
import com.lianlian.global.payment.sdk.support.LLPayException;
import com.lianlian.global.payment.sdk.support.Pair;
import com.lianlian.global.payment.sdk.support.RespError;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author thaipay
 * @since 1.0
 */
@Slf4j
public final class HttpClientUtils {

    private static final int CONNECTION_TIMEOUT = 5000;
    private static final int CONNECTION_REQUEST_TIMEOUT = 1000;
    private static final int SOCKET_TIMEOUT = 15000;

    private static final CloseableHttpClient HTTP_CLIENT;

    static {

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(100);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT)
                .setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();

        HttpRequestRetryHandler retryHandler = new StandardHttpRequestRetryHandler();

        HTTP_CLIENT = HttpClients.custom().setConnectionManager(connectionManager).setDefaultRequestConfig
                (requestConfig).setRetryHandler(retryHandler).build();
    }


    public static Pair<Map<String, String>, String> doGet(String uri, Map<String, String> headers,
                                                   Map<String, String> params) {

        CloseableHttpResponse response = null;
        try {

            URIBuilder builder = new URIBuilder(uri);
            List<NameValuePair> list = new ArrayList<>();
            for (Map.Entry<String, String> param : params.entrySet()) {
                list.add(new BasicNameValuePair(param.getKey(), param.getValue()));
            }
            builder.setParameters(list);
            HttpGet httpGet = new HttpGet(builder.build());
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpGet.addHeader(new BasicHeader(header.getKey(), header.getValue()));
            }
            long begin = System.currentTimeMillis();
            log.info("call lianlian pay service request. \n======== uri: {}\n======== header: {}\n======== body: {}\n",
                    uri, JSON.toJSONString(headers), JSON.toJSONString(params));
            response = HTTP_CLIENT.execute(httpGet);

            String respBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();

            Map<String, String> headerMap = filterHeader(response.getAllHeaders());
            log.info("call lianlian pay service response. \n======== status: {}, cost: {}ms\n======== header: {}\n======== body: {}\n",
                    statusCode, System.currentTimeMillis() - begin, JSON.toJSONString(headerMap), respBody);

            if (statusCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                throw new LLPayException(RespError.SERVICE_UNAVAILABLE);
            } else if (statusCode >= HttpStatus.SC_BAD_REQUEST) {
                throw new LLPayException(RespError.BAD_REQUEST);
            }

            if (statusCode == HttpStatus.SC_OK) {
                return Pair.of(headerMap, respBody);
            } else {
                throw new LLPayException(RespError.SERVICE_UNAVAILABLE);
            }
        } catch (HttpHostConnectException | ConnectTimeoutException e) {
            log.error("can not connect to lianlian timeout!", e);
            throw new LLPayException(RespError.NETWORK_ERROR);
        } catch (SocketTimeoutException e) {
            log.error("connect to lianlian timeout!", e);
            throw new LLPayException(RespError.SERVICE_TIMEOUT);
        } catch (Throwable t) {
            log.error("call lianlian pay service exception!", t);
            throw new LLPayException(RespError.SERVICE_UNAVAILABLE);
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("close response exception!", e);
            }
        }
    }


    public static Pair<Map<String, String>, String> doPost(String uri, Map<String, String> headers, String body)
            throws LLPayException {

        CloseableHttpResponse response = null;
        try {

            HttpPost httpPost = new HttpPost(uri);
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpPost.addHeader(new BasicHeader(header.getKey(), header.getValue()));
            }
            httpPost.setEntity(new StringEntity(body, GlobalConst.CHARSET_UTF8));
            long begin = System.currentTimeMillis();
            log.info("call lianlian pay service request. \n======== uri: {}\n======== header: {}\n======== body: {}\n",
                    uri, JSON.toJSONString(headers), body);
            response = HTTP_CLIENT.execute(httpPost);

            String respBody = EntityUtils.toString(response.getEntity());
            int statusCode = response.getStatusLine().getStatusCode();

            Map<String, String> headerMap = filterHeader(response.getAllHeaders());
            log.info("call lianlian pay service response. \n======== status: {}, cost: {}ms\n======== header: {}\n======== body: {}\n",
                    statusCode, System.currentTimeMillis() - begin, JSON.toJSONString(headerMap), respBody);

            if (statusCode >= HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                throw new LLPayException(RespError.SERVICE_UNAVAILABLE);
            } else if (statusCode >= HttpStatus.SC_BAD_REQUEST) {
                throw new LLPayException(RespError.BAD_REQUEST);
            }

            if (statusCode == HttpStatus.SC_OK) {
                return Pair.of(headerMap, respBody);
            } else {
                throw new LLPayException(RespError.SERVICE_UNAVAILABLE);
            }
        } catch (HttpHostConnectException | ConnectTimeoutException e) {
            log.error("can not connect to lianlian timeout!", e);
            throw new LLPayException(RespError.NETWORK_ERROR);
        } catch (SocketTimeoutException e) {
            log.error("connect to lianlian timeout!", e);
            throw new LLPayException(RespError.SERVICE_TIMEOUT);
        } catch (Throwable t) {
            log.error("call lianlian pay service exception!", t);
            throw new LLPayException(RespError.SERVICE_UNAVAILABLE);
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("close response exception!", e);
            }
        }
    }

    private static Map<String, String> filterHeader(Header[] headers) {

        return Stream.of(headers).filter(h -> h.getName().contains(GlobalConst.SIGNATURE)).collect(Collectors.toMap
                (Header::getName, Header::getValue));
    }
}
