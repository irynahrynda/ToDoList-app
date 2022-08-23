package com.example.todolistapp.config;

import com.example.todolistapp.model.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String ROLE_ADMIN = Role.RoleName.ADMIN.name();
    private static final String ROLE_USER = Role.RoleName.USER.name();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers(HttpMethod.GET,"/tasks",
//                        "/taskslists").hasAnyRole(ROLE_ADMIN,ROLE_USER)
//                .antMatchers(HttpMethod.POST,"/tasks", "/taskslists")
//                .hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.PUT,"/taskslists").hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.DELETE, "/taskslists").hasRole(ROLE_ADMIN)
//                .antMatchers(HttpMethod.GET, "/taskslists")
//                .hasRole(ROLE_USER)
//                .antMatchers(HttpMethod.POST, "/taskslists").hasRole(ROLE_USER)
//                .antMatchers(HttpMethod.PUT, "/taskslists").hasRole(ROLE_USER)
//                .antMatchers(HttpMethod.GET, "/users/by-email").hasRole(ROLE_ADMIN)
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
