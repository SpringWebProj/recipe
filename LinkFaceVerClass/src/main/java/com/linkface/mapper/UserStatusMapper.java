package com.linkface.mapper;

import org.apache.ibatis.annotations.Param;

import com.linkface.entity.UserStatus;

public interface UserStatusMapper {

	void insert(Long userKey);
	
	UserStatus select(Long userKey);
	
	void updateToken(Long userKey);
	
	void updateEmailAuth(Long userKey);
	
	void updateRole(@Param("role") String role, @Param("userKey") Long userKey);
	
}
