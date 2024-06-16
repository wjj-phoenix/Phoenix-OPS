package com.phoenix.devops.utils.terminal;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @author wjj-phoenix
 * @since 2024-06-16
 */
@Data
public class SessionInfo {
    /**
     * 缓存标识
     */
    private String id;

    /**
     * 主机
     */
    private String host;

    /**
     * 端口
     */
    private int port;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public static SessionInfoBuilder builder(String host) {
        return new SessionInfoBuilder(host);
    }


    public static class SessionInfoBuilder {
        private final SessionInfo info;

        private SessionInfoBuilder(String host) {
            this.info = new SessionInfo();
            info.host = host;
        }

        public SessionInfoBuilder port(int port) {
            Assert.isTrue(port > 0, "port必须大于0");
            this.info.port = port;
            return this;
        }

        public SessionInfoBuilder id(String id) {
            this.info.id = id;
            return this;
        }

        public SessionInfoBuilder username(String username) {
            this.info.username = username;
            return this;
        }

        public SessionInfoBuilder password(String password) {
            this.info.password = password;
            return this;
        }

        public SessionInfo build() {
            if (StrUtil.isEmpty(info.username)) {
                info.username = "root";
            }
            // 如果主机id为空，则host作为默认的缓存key
            if (StrUtil.isEmpty(info.id)) {
                info.id = info.host;
            }
            return info;
        }
    }
}
