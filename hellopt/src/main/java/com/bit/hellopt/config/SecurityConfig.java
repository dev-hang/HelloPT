package com.bit.hellopt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bit.hellopt.service.user.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailService customUserDetailService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/main").permitAll()
				.antMatchers("/user/**").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/deleteexerciseinfo").hasRole("ADMIN")
				.antMatchers("/updteeexerciseinfo").hasRole("ADMIN")
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/file/**").permitAll()
				.antMatchers("/meetingOne").authenticated() //모임 상세보기 로그인시에만 사용 가능(선하)
				.antMatchers("/meetingWrite").authenticated() //모임 글쓰기 로그인시에만 사용 가능(선하)
				.antMatchers("/classlist").authenticated()
				.antMatchers("/meal").authenticated() //로그인시에만 사용 가능
				.antMatchers("/auth/**").authenticated()
				.antMatchers("/calender").authenticated() //캘린더 로그인시에만 사용 가능(선하)
				.antMatchers("/calender").authenticated()
				.antMatchers("/multi").authenticated()
		    //.anyRequest().authenticated()
			.and()
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/main")
				.failureUrl("/login?error=true")
				.permitAll()
			.and()
				.logout()
				.logoutSuccessUrl("/main")
				.deleteCookies("JSESSIONID")
				//.invalidateHttpSession(true)
				.permitAll()
			.and()
				.rememberMe().key("uniqueAndSecret").rememberMeParameter("remember-me")
			.and()
				.csrf().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
