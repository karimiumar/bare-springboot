package com.umar.apps.bare.spring;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

public class TomcatLauncher {
    private static final Logger LOGGER = LoggerFactory.getLogger( TomcatLauncher.class );
    private static final int port = 8080;
    @PostConstruct
    public void start() throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.setPort(port);
        tomcat.getConnector().setProperty("maxThreads", "10");//Won't work without this statement
        var ctx = tomcat.addContext("", null);
        Tomcat.addServlet(ctx, "testServlet", new MyServlet());
        ctx.addServletMappingDecoded("/", "testServlet");
        tomcat.start();
        LOGGER.info("-----------------------------------------------------------------");
        LOGGER.info("Starting Tomcat on PORT {} ", port);
        LOGGER.info("-----------------------------------------------------------------");
        new Thread(() -> tomcat.getServer().await()).start();
        LOGGER.info("Tomcat started on host {} - port {}", tomcat.getHost().getName(), port);
    }
}
