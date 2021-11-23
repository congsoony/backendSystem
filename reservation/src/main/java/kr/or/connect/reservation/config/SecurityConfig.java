package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 해당경로에 대한 요청은 인증/인가 처리하지 않도록 무시합니다.
		web.ignoring().antMatchers("/api/categories","/api/displayinfos**","/api/promotions","/api/displayinfos/**");
	}
	
}
