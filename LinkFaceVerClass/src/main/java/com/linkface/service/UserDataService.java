package com.linkface.service;

import java.util.List;

import com.linkface.entity.UserData;

public interface UserDataService {
	public List<UserData> getJJim(Long userKey);
	public void deleteJJim(UserData userdata);
	public void insertJJim(UserData userdata);
	public void regfoodingredients(UserData userdata);
}
