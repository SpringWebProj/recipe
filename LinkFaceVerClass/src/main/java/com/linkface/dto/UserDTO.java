package com.linkface.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {

	private String key;
	
	private String id;
	
	private String password;
	
	private String passwordCheck;
	
	private String email;
	
	private String name;
	
	private int jjimnumber;
	
}
