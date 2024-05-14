package com.demo.gateway.engine.handler.send;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.groovy.GroovyUtil;
import com.demo.gateway.model.HttpSendConf;
import com.demo.gateway.model.MessageType;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Http发送处理器
 *
 * @author 隐墨星辰
 */
@Component
@Log4j2
public class Httpclient {

    public void send(HandlerEngineContext context) {
        // 创建一个默认的HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpSendConf httpSendConf = context.getInterfaceConf().getHttpSendConf();

        // 创建HttpPost对象，传入目标服务器的URL
        String host = GroovyUtil.make(httpSendConf.getHost(), context.getRuntimeContext());
        String url = GroovyUtil.make(httpSendConf.getUrl(), context.getRuntimeContext());
        HttpPost httpPost = new HttpPost(host + url);

        // 设置请求头，这里我们设置Content-Type为application/json
        if (MessageType.JSON.equals(context.getInterfaceConf().getMessageType())) {
            httpPost.setHeader("Content-Type", "application/json");
        }

        // 创建一个StringEntity对象，将请求消息作为UTF-8编码的字符串传入
        StringEntity entity = new StringEntity(context.getAssembledRequestMessage(), "UTF-8");

        // 将创建的entity设置为httpPost的请求体
        httpPost.setEntity(entity);

        try {
            // 执行httpPost请求，获取服务器返回的响应
            CloseableHttpResponse response = httpClient.execute(httpPost);

            // 从响应中获取实体
            HttpEntity responseEntity = response.getEntity();

            // 如果响应实体不为空，将其转换为字符串，并设置为context的原始响应消息
            if (responseEntity != null) {
                context.setOriginalResponseMessage(EntityUtils.toString(responseEntity));
            }
        } catch (Exception e) {
            // 如果在执行请求或处理响应时发生错误，记录错误信息
            log.error("Error occurred while sending post request", e);

            throw new GatewayException(e);
        } finally {
            try {
                // 关闭HttpClient
                httpClient.close();
            } catch (IOException e) {
                // 如果在关闭HttpClient时发生错误，记录错误信息
                log.error("Error occurred while closing http client", e);
            }
        }
    }
}
