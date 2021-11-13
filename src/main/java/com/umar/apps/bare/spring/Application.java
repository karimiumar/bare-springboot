package com.umar.apps.bare.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger( Application.class );
    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext(MyConfiguration.class);
        var ds = ctx.getBean(DataSource.class);
        try(var conn = ds.getConnection()) {
            var rs = conn.createStatement()
                    .executeQuery("SELECT RANDOM_UUID() as random");
            while (rs.next()) {
                var random = rs.getString("random");
                LOGGER.info("Received a Random Number from database {}",random);
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        LOGGER.info("Bare Spring Boot's UP...");
    }
}
