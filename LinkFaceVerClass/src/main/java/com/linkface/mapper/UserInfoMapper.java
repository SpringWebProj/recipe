package com.linkface.mapper;

import org.apache.ibatis.annotations.Param;

import com.linkface.entity.UserInfo;

public interface UserInfoMapper {

	void insert(UserInfo userinfo);
	
	UserInfo select(Long userKey);
	
	UserInfo readId(String userId);
	
	UserInfo readEmail(String userEmail);
	
	void updateEmail(@Param("userEmail") String userEmail,@Param("userKey") Long userKey);
	
	void updatePassword(@Param("userPassword") String userPassword,@Param("userKey") Long userKey);
	
	void updateName(@Param("userName") String userName,@Param("userKey") Long userKey);
	
	void changeUpdateDate(Long userKey);
}
