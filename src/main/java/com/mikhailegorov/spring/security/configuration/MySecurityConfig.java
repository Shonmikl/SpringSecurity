package com.mikhailegorov.spring.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
//@EnableWebSecurity включает поддержку web security и обеспечивает интеграцию со Spring MVC
@RequiredArgsConstructor
//Он также расширяет WebSecurityConfigurerAdapter
// и переопределяет пару методов для установки некоторых настроек безопасности.
//
//Метод configure(HttpSecurity) определяет, какие URL пути должны быть защищены, а какие нет
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

    final DataSource dataSource;

    @Override
    //AuthenticationManagerBuilder создает в памяти хранилище пользователей
    // с единственным пользователем. Этому пользователю
    // дано имя "user", пароль "password" и роль "ROLE".
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
   }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .antMatchers("/hr_info").hasAnyRole("HR")
                .antMatchers("/manager_info").hasAnyRole("MANAGER")
                .and().formLogin().permitAll();
    }
}