package com.linkface.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.linkface.config.RootConfig;
import com.linkface.config.SecurityConfig;
import com.linkface.entity.UserData;
import com.linkface.mapper.UserDataMapper;

import lombok.Setter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class,SecurityConfig.class})
public class UserDataTests {
	@Setter(onMethod_=@Autowired)
	public UserDataMapper mapper;
	
	@Test
	public void testMapper() {
		List<UserData> a = mapper.select(1L);
		a.forEach(i->{System.out.println(i.getJjim());});
	}
}
