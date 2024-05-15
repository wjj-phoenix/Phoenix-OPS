package com.phoenix.devops.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author wjj-phoenix
 * @since 2024-05-15
 * 创建WebSocket握手拦截器:实现HandshakeInterceptor接口，覆盖beforeHandshake和afterHandshake方法。允许在握手前后执行自定义的逻辑
 */
@Log4j2
public class WebsocketAuthInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // 在此方法中进行请求验证、设置属性等操作
        // 返回true允许握手继续，false则拒绝
        log.info("握手后操作-start-beforeHandshake");
        log.info("request: {}", request);
        log.info("request getLocalAddress: {}", request.getLocalAddress());
        log.info("request getURI: {}", request.getURI());
        log.info("request getMethod: {}", request.getMethod());
        log.info("request getRemoteAddress: {}", request.getRemoteAddress());
        log.info("request getBody: {}", request.getBody());
        log.info("request getPrincipal: {}", request.getPrincipal());
        log.info("request getAsyncRequestControl: {}", request.getAsyncRequestControl(response));
        log.info("response: {}", response);
        log.info("wsHandler: {}", wsHandler);
        log.info("attributes: {}", attributes);
        log.info("握手后操作-end-beforeHandshake");
        return isRequestAuthorized(request); // 自定义验证逻辑
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("握手后执行的清理操作-start-afterHandshake");
        log.info("request: {}", request);
        log.info("response: {}", response);
        log.info("wsHandler: {}", wsHandler);
        log.info("exception: {}", exception);
        log.info("握手后执行的清理操作-end-afterHandshake");
        // 握手后执行的清理操作
    }

    private boolean isRequestAuthorized(ServerHttpRequest request) {
        // 实现你的授权逻辑，例如检查请求头中的Token
        // 示例代码，实际应用中应替换为真实验证逻辑
        return true;
    }


}
