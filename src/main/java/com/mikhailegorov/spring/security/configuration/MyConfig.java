package com.mikhailegorov.spring.security.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@ComponentScan(basePackages = "com.mikhailegorov.spring.security")
@Configuration
@EnableWebMvc // эта аннотация разрешает нашему проекту использовать MVC
public class MyConfig {

    @Bean // ViewResolver Spring MVC определяет какое представление
    // необходимо отобразить для определенного запроса
    public ViewResolver viewResolver () {
        //Имена Видов (View) будут определяться при помощи
        // InternalResourceViewResolver. В соответствии с указанным выше правилом,
        // Вид с именем hello будет реализован по адресу /WEB-INF/jsp/hello.jsp
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/view/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }

    @Bean
    //Database Connection Pool (dbcp) —
    // это способ решения изложенной выше проблемы.
    // Он подразумевает, что в нашем распоряжении имеется
    // некоторый набор («пул») соединений к базе данных.
    // Когда новый пользователь запрашивает доступ к БД,
    // ему выдаётся уже открытое соединение из этого пула.
    // Если все открытые соединения уже заняты, создаётся новое.
    // Как только пользователь освобождает одно из уже существующих соединений,
    // оно становится доступно для других пользователей.
    // Если соединение долго не используется, оно закрывается.
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/security?useSSL=false&serverTimezone=UTC");
            dataSource.setUser("root");
            dataSource.setPassword("123123");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}