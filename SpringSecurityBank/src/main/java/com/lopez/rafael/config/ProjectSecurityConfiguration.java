package com.lopez.rafael.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

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
    }
}
