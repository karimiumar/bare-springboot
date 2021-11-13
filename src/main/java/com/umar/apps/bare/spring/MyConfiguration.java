package com.umar.apps.bare.spring;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;

@PropertySources({
        @PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:application.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "classpath:application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
})
public class MyConfiguration {

    @Bean
    @Conditional(value = TomcatOnClassPathCondition.class)
    public TomcatLauncher launcher() {
        return new TomcatLauncher();
    }

    @Bean
    @Conditional(value = ActivateDataSourceCondition.class)
    public DataSource dataSource(Environment environment) throws
            SQLException, ClassNotFoundException,IllegalAccessException,InstantiationException {
        var driverClassName = environment.getProperty("spring.datasource.driver-class-name");
        var url = environment.getProperty("spring.datasource.url");
        return new SimpleDriverDataSource((Driver) Class.forName(driverClassName).newInstance(), url);
    }

}
