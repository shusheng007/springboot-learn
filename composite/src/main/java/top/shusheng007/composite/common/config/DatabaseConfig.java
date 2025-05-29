package top.shusheng007.composite.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Ben.Wang
 * <p>
 * author     : Ben.Wang
 * date       : 2021/5/11 17:33
 * description:
 */

//@Configuration
@ConfigurationProperties(prefix="database")
public class DatabaseConfig {
    /**
     * 数据库用户名
     */
    private String userName;
    /**
     * 数据库密码
     */
    private String passWord;
    /**
     * 数据库服务器
     */
    private Server server;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    @Override
    public String toString() {
        return "UserConfig{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", server=" + server +
                '}';
    }

    public static class Server{
        /**
         * 服务器IP
         */
        private String ip;
        /**
         * 服务器端口号
         */
        private int port;

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "ip='" + ip + '\'' +
                    ", port=" + port +
                    '}';
        }
    }


}
