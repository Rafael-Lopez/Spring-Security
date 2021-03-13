package com.lopez.rafael.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ProjectSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /*
     * /account - Secured
     * /balance - Secured
     * /loan - Secured
     * /card - Secured
     * /notices - Not secured
     * /contact - Not secured
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        /*
         * Default configuration which will secure all requests
         */
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

        /*
         * Custom configuration as per our requirements
         */
        http
            .authorizeRequests()
                .antMatchers("/account").authenticated()
                .antMatchers("/balance").authenticated()
                .antMatchers("/loan").authenticated()
                .antMatchers("/card").authenticated()
                .antMatchers("/notices").permitAll()
                .antMatchers("/contact").permitAll()
            .and()
            .formLogin()
            .and()
            .httpBasic();

        /*
         * Configuration to deny all requests
         */
//        http.authorizeRequests().anyRequest().denyAll().and().formLogin().and().httpBasic();

        /*
         * Configuration to permit all requests
         */
//        http.authorizeRequests().anyRequest().permitAll().and().formLogin().and().httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("admin").password("admin").authorities("admin")
                .and()
                .withUser("user").password("user").authorities("read")
                .and()
                //Always pass it. If no passwordEncoder, Spring doesn't know how to process password -> throws error
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
