package com.example.SpringSecurityHW8.configs;


import com.example.SpringSecurityHW8.utils.DateFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.example.SpringSecurityHW8.repositories")
@EnableTransactionManagement
@ComponentScan("com.example.SpringSecurityHW8")
public class AppConfig {

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }

}
