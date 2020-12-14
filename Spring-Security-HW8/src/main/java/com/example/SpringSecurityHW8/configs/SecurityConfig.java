package com.example.SpringSecurityHW8.configs;

import com.example.SpringSecurityHW8.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity//(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public void setUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasAnyRole( "MANAGER","ADMIN", "SUPER_ADMIN")
       //         .antMatchers("/admin/oders").hasAnyRole( "MANAGER","ADMIN", "SUPER_ADMIN")
                .antMatchers("/cart/createOrder**").authenticated()
                .antMatchers("/registration**").anonymous()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout() // This is missing and is important
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}11")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .roles("USER", "ADMIN")
//                .build();
//
//        UserDetails sa = User.builder()
//                .username("sa")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .roles("USER", "ADMIN", "SUPER_ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin, sa);
//    }

//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password("{noop}11")
//                .authorities("ROLE_USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .authorities("ROLE_USER", "ROLE_ADMIN")
//                .build();
//
//        UserDetails sa = User.builder()
//                .username("sa")
//                .password("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK")
//                .authorities("ROLE_USER", "ROLE_ADMIN", "ROLE_SUPER_ADMIN")
//                .build();
//
//        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
//
//        if(!manager.userExists(user.getUsername())){
//            manager.createUser(user);
//        }
//        if(!manager.userExists(admin.getUsername())){
//            manager.createUser(admin);
//        }
//        if(!manager.userExists(sa.getUsername())){
//            manager.createUser(sa);
//        }
//        return manager;
//    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

}
