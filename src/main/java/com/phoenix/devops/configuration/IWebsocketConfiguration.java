package com.phoenix.devops.configuration;

import com.phoenix.devops.handler.IWebTerminalHandler;
import com.phoenix.devops.interceptor.WebsocketAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author wjj-phoenix
 * @since 2024-05-14
 */
@Configuration
@EnableWebSocket
public class IWebsocketConfiguration implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        // 注册WebSocket处理器和对应的URL路径
        registry.addHandler(webTerminalHandler(), "/machines/terminal").setAllowedOrigins("*")
                // 配置WebSocketHandler和拦截器：在WebSocket配置类中，通过WebSocketConfigurer接口注册WebSocket处理程序，并添加你的握手拦截器。
                .addInterceptors(new WebsocketAuthInterceptor());
    }

    // 提供WebSocket处理器实例
    public WebSocketHandler webTerminalHandler() {
        return new IWebTerminalHandler();
    }
}
