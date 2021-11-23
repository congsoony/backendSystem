package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {
		// 해당경로에 대한 요청은 인증/인가 처리하지 않도록 무시합니다.
		web.ignoring().antMatchers("/api/categories", "/api/displayinfos**", "/api/promotions", "/api/displayinfos/**"
		// swagger관련 리소스 시큐리티 필터 제거
				, "/v2/api-docs", "/swagger-resources/**", "swagger-ui.html", "/webjars/**", "/swagger/**");
	}

	// 패스워드 인코더를 빈으로 등록합니다. 암호를 인코딩하거나,
	// 인코딩된 암호와 사용자가 입력한 암호가 같은 지 확인할 때 사용합니다.
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
