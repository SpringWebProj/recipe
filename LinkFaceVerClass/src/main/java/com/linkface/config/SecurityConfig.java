package com.linkface.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.linkface.security.AccountAccessDeniedHandler;
import com.linkface.security.AccountAuthenticationProvider;
import com.linkface.security.AccountLoginSuccessHandler;
import com.linkface.security.AccountLogoutSuccessHandler;
import com.linkface.security.AccountUserDetailService;
import com.linkface.security.NonAccountDeniedHandler;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public AuthenticationSuccessHandler accountLoginSuccessHandler()
	{ 	return new AccountLoginSuccessHandler();	}
	@Bean
	public LogoutSuccessHandler accountLogoutSuccessHandler()
	{	return new AccountLogoutSuccessHandler();	}
	@Bean
	public AccessDeniedHandler accountAccessDeniedHandler()
	{	return new AccountAccessDeniedHandler();	}
	@Bean
	public AuthenticationEntryPoint nonAccountDeniedHandler()
	{	return new NonAccountDeniedHandler();	}
	@Bean
	public UserDetailsService accountUserDetailService()
	{	return new AccountUserDetailService();	}
	@Bean
	public PasswordEncoder bCryptPasswordEncoder()
	{	return new BCryptPasswordEncoder(); }
	@Bean
	public AuthenticationProvider accountAuthenticationProvider()
	{	return new AccountAuthenticationProvider(); }
	@Bean
	public SessionRegistry sessionRegistry() {
		return new SessionRegistryImpl();
	}
			
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		// ????????????
		http.authorizeRequests()
		 		.antMatchers("/").permitAll()
		 		.antMatchers("/main").permitAll()
		 		.antMatchers("/singup").permitAll()
		 		.antMatchers("/member").access("isAuthenticated()")
		 		.antMatchers("/admin").access("hasAnyRole('ROLE_MANAGER','ROLE_ADMIN')");
		// ?????????
		http.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/login")
					.successHandler(accountLoginSuccessHandler());
		// ????????????
		http.logout()
				.logoutUrl("/logout")
				.invalidateHttpSession(true)
				.logoutSuccessHandler(accountLogoutSuccessHandler());
		
		// ?????? ??????
		http.exceptionHandling()
				.accessDeniedHandler(accountAccessDeniedHandler())
				.authenticationEntryPoint(nonAccountDeniedHandler());
		// ?????? ??????
		http.csrf()
				.ignoringAntMatchers("/login");
		http.csrf()
				.ignoringAntMatchers("/react/resp");
		// ?????? ????????? (?????? ???)
		http.rememberMe()
				.rememberMeParameter("remember")
				.rememberMeCookieName("linkface-remember")
				.rememberMeCookieDomain("linkface")
				.tokenValiditySeconds(60 * 60 * 24 * 30)
				.userDetailsService(accountUserDetailService());
		// ?????? ?????? (?????? ???)
		  http.sessionManagement()
		          	.maximumSessions(1)
		          	.maxSessionsPreventsLogin(true)
		          	.expiredUrl("/duplicated-login")
		          	.sessionRegistry(sessionRegistry());
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(accountAuthenticationProvider());
	}
}