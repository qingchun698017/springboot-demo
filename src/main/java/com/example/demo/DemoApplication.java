package com.example.demo;

import org.apache.catalina.connector.Connector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("com.example.demo.web.mapper")
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(httpConnector());
        return tomcat;
    }
    
    //spring boot 1 的写法
    //    @Bean
    //    public EmbeddedServletContainerFactory servletContainer(){
    //        TomcatEmbeddedServletContainerFactory tomcat=new TomcatEmbeddedServletContainerFactory(){
    //            @Override
    //            protected void postProcessContext(Context context) {
    //                SecurityConstraint securityConstraint=new SecurityConstraint();
    //                securityConstraint.setUserConstraint("CONFIDENTIAL");//confidential
    //                SecurityCollection collection=new SecurityCollection();
    //                collection.addPattern("/*");
    //                securityConstraint.addCollection(collection);
    //                context.addConstraint(securityConstraint);
    //            }
    //        };
    //        tomcat.addAdditionalTomcatConnectors(httpConnector());
    //        return tomcat;
    //    }

    @Bean
    public Connector httpConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(8080);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}
