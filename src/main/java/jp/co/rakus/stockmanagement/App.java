package jp.co.rakus.stockmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@EnableAutoConfiguration
@ComponentScan
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
	@Bean
    public StandardPasswordEncoder encorder(){
    	return new StandardPasswordEncoder();
    }
    
}
