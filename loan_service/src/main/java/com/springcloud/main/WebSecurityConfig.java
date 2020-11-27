package com.springcloud.main;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                /*.withUser("user")
                .password("{noop}user")
                .roles("USER")
            .and()*/
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN")
            ;
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.authorizeRequests()
        	//.antMatchers("/bank/*").hasAnyRole("USER", "ADMIN")
        	//.antMatchers("*/status?*").hasRole("ADMIN")
        	.antMatchers("/status/{loanNumber}/**").authenticated()
           .anyRequest().permitAll()
        	//.antMatchers("/**").permitAll()
            .and()
            .httpBasic();
    }  
}
