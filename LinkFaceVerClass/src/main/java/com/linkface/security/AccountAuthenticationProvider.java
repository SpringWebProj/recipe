package com.linkface.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Setter;


public class AccountAuthenticationProvider implements AuthenticationProvider {

	@Setter(onMethod_=@Autowired)
	private PasswordEncoder bCryptPasswordEncoder;
	
	@Setter(onMethod_=@Autowired)
	private AccountUserDetailService accountUserDetailService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("들어옵니까?"+authentication);
//		if(authentication == null){
//			System.out.println("null이유");
//			throw new InternalAuthenticationServiceException("Authentication is null"); 
//		}
		
		boolean permit = false;
		
		// 입력 아이디
		String id = authentication.getName();
		
		// 입력 패스워드
		String password = (String) authentication.getCredentials();
	
		// DB 에서 해당 ID 조회
		AccessAccount account = (AccessAccount) accountUserDetailService.loadUserByUsername(id);
		
		if(account == null)	{ 
			System.out.println("에러1");
			throw new BadCredentialsException(id); 
		}
		else if(!account.isAccountNonExpired()) { 
			System.out.println("에러1");
			throw new AccountExpiredException(id);
		} 
		else if(!account.isAccountNonLocked()) { 
			System.out.println("에러1");
			throw new LockedException(id); 
		} 
		else if(!account.isCredentialsNonExpired()) { 
			System.out.println("에러1");
			throw new CredentialsExpiredException(id); 
		} 
		else if(!account.isEnabled()) {
			System.out.println("에러1");
			throw new DisabledException(id); 
		}
		
		// 필요하다면 로직 수정
		permit = password.length() >= 59 ?
						password.equals(account.getPassword()):
						bCryptPasswordEncoder.matches(password, account.getPassword());
		System.out.println("글자수체크 " +permit);
		// 인증이 완료되었다면 객체 반환 아니면 null 반환
		System.out.println("생성완료 "  +new UsernamePasswordAuthenticationToken(account,account.getPassword(),account.getAuthorities()));
		return new UsernamePasswordAuthenticationToken(account,account.getPassword(),account.getAuthorities()) ;
	
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// authentication 이 UsernamePasswordAuthenticationToken 일 경우 실행
		System.out.println("지원하는가?" + authentication.getName());
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
		
	}
	
	
	
}

