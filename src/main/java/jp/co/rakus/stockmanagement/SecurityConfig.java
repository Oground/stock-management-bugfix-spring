package jp.co.rakus.stockmanagement;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/webjars/**","/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().antMatchers("/loginForm").permitAll().anyRequest().authenticated();
		http.formLogin().loginProcessingUrl("/login").loginPage("/loginForm").failureUrl("/loginForm").defaultSuccessUrl("/book/list", true)
		.usernameParameter("mailAddress").passwordParameter("password").and();
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
		.logoutSuccessUrl("/loginForm");
	}
}
