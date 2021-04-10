package com.lopez.rafael.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Collections;

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
            .cors()
                .configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setMaxAge(3600L);

                        return config;
                    }
                })
            .and()
            //.csrf().disable()
            //withHttpOnlyFalse() ensures that the UI application can read the token/cookie. For example, when the user
            //logs in via the login page, Spring sends a response for the request along with Set-cookie header which
            //contains a securely generated XSRF-TOKEN. Then, when sending a state-changing request (e.g. POST) the
            //client copies the cookie value to the HTTP request header. The request is sent with both header and cookie
            //(the browser attaches the cookie automatically), and Spring compares the header and the cookie values,
            //if they are the same the request is accepted, otherwise, 403 is returned to the client.
            //https://stackoverflow.com/a/62650184
            .csrf()
                //csrf should not be enabled for /contact page, but it should for the rest
                .ignoringAntMatchers("/contact")
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
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

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("admin").password("admin").authorities("admin")
//                .and()
//                .withUser("user").password("user").authorities("read")
//                .and()
//                //Always pass it. If no passwordEncoder, Spring doesn't know how to process password -> throws error
//                .passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    /*
     * Another way to do the same as above. This one is clearer, but requires a PasswordEncoder bean
     */
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//        UserDetails admin = User.withUsername("admin").password("admin").authorities("admin").build();
//        UserDetails user = User.withUsername("user").password("user").authorities("read").build();
//        userDetailsService.createUser(admin);
//        userDetailsService.createUser(user);
//
//        auth.userDetailsService(userDetailsService);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Another way to do the same as above. This one actually uses a DB, therefore, no need to create
     * users via code since users already exists in the DB. All we need is a UserDetailsService bean
     * of type JdbcUserDetailsManager
     */
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
}
