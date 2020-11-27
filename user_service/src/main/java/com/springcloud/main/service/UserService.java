package com.springcloud.main.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.main.client.LoanClient;
import com.springcloud.main.dao.UserDao;
import com.springcloud.main.entity.Loan;
import com.springcloud.main.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao usertDao;

	
	//@HystrixCommand(fallbackMethod = "getUserLoansFallBack")
	public List<Loan> getUserLoans(Long userId) {
		return null;
	}

	public User addUser(User user) {
		return usertDao.addUser(user);
	}

	public User getUserByUserId(Integer id) {
		return usertDao.getUserByUserId(id);
	}
	
	
	public List<Loan> getUserLoansFallBack(Long userId) {
		System.out.println("############# getUserLoansFallback called#######################");
		return Collections.emptyList();
	}


}
