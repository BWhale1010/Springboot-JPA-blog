package com.bw.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bw.blog.config.auth.PrincipalDetailService;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것

@Configuration // 빈등록(IoC 관리)
@EnableWebSecurity // 시큐리티 필터가 등록 됨
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크
public class SecurityConfig {
	
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	@Bean //  IoC 가 되어 스프링이 관리
	BCryptPasswordEncoder encodePWD() {
	
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	    }
	

	
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트 시)
			.authorizeRequests()
			.antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
			.permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.formLogin()
			.loginPage("/auth/loginForm")
			.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 오는 요청 로그인을 가로채서 대신 로그인 해줌
			.defaultSuccessUrl("/");  
		
		return http.build();
		
	}

}
