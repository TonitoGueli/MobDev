package com.mobdev.rickandmortyapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: Anthonny Gueli
 * Contains a single method intended to bypass SpringSecurity configuration in a specific scenario.
* */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
    * This method overrides the "configure" method inside WebSecurityConfigurerAdapter
    * This is intended so the h2 connection to the console is not affected.
    * If you delete this method you won't be able to access to the h2-console.
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}