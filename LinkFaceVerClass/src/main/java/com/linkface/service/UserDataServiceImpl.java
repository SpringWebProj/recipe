package com.linkface.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linkface.entity.UserData;
import com.linkface.mapper.UserDataMapper;

import lombok.Setter;

@Service
public class UserDataServiceImpl implements UserDataService{
	@Setter(onMethod_=@Autowired)
	public UserDataMapper mapper;

	public List<UserData> getJJim(Long userKey) {
		List<UserData> result = mapper.select(userKey);
		
		return result;
	}

	@Override
	public void deleteJJim(UserData userdata) {
		mapper.delete(userdata);
	}

	@Override
	public void insertJJim(UserData userdata) {
		mapper.insert(userdata);
	}

	@Override
	public void regfoodingredients(UserData userdata) {
		mapper.insertfooditem(userdata);		
	}
	
	
}
