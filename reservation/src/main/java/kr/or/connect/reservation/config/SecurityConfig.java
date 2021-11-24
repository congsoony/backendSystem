package kr.or.connect.reservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.connect.reservation.service.security.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// 해당경로에 대한 요청은 인증/인가 처리하지 않도록 무시합니다.
		web.ignoring().antMatchers("/api/categories", "/api/displayinfos**", "/api/promotions", "/api/displayinfos/**"
		// swagger관련 리소스 시큐리티 필터 제거
				, "/v2/api-docs", "/swagger-resources/**", "swagger-ui.html", "/webjars/**", "/swagger/**");
	}


	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				.antMatchers("/main", "/users/loginerror").permitAll()
				//로그인도 되어있어야하고 USER라는 권한을 가지고 있어야 접근할수있음
				.antMatchers("/api/reservationInfos").hasAnyRole("USER","ADMIN")
				.anyRequest().authenticated()
				.and()
					//loginform을 보여주는 controller 메소드가 작성되어있어야함 UserController에 /users/lgoinform으로 작성되어있음
					.formLogin().loginPage("/users/loginform")
					//loginform에서 input tag의 이름이 userId 와 password로 되어있어야함
					.usernameParameter("userId")
					.passwordParameter("password")
					
					//그것을처리하는 경로가 설정이 되어있고 내가구현하는것이 아님 spring security 필터가 처리해줌 그렇지만 
					//form tag의 action 부분이 /reservation/authenticate라고 설정해줘야함
					.loginProcessingUrl("/authenticate")
					//로그인이 실패 했다면 이경로로 포워딩 마찬가지로 Controller 메소드가 작성되어야함
					.failureForwardUrl("/users/loginerror?login_error=1")
					//로그인 성공시 /경로로 redirect
					.defaultSuccessUrl("/main", true)
					.permitAll()
				.and()
				//로그아웃에대한 요청이 들어오면 세션에서 로그인정보를 다 삭제한후
					.logout()
					.logoutUrl("/logout")
					// /경로로 redirect
					.logoutSuccessUrl("/users/loginform");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	// 패스워드 인코더를 빈으로 등록합니다. 암호를 인코딩하거나,
	// 인코딩된 암호와 사용자가 입력한 암호가 같은 지 확인할 때 사용합니다.
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
