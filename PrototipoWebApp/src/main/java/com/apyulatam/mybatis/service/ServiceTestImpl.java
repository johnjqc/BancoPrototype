package com.apyulatam.mybatis.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceTestImpl implements ServiceTest {

	@Override
	public String getName() {
		return "John";
	}
	
	

}
