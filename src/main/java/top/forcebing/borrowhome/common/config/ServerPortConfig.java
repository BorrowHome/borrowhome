package top.forcebing.borrowhome.common.config;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liliangbin  dumpling1520@gmail.com
 * @date 2019/2/28  10:44
 **/
@Configuration
public class ServerPortConfig {

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createHTTPConnector());
        return tomcat;
    }

    private Connector createHTTPConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

        connector.setScheme("http");
        connector.setSecure(false);
        connector.setPort(8082);
        connector.setRedirectPort(8083);
        return connector;
    }

}
