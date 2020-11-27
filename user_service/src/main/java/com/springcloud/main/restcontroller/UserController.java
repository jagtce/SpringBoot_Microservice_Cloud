package com.springcloud.main.restcontroller;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springcloud.main.client.LoanClient;
import com.springcloud.main.entity.Loan;
import com.springcloud.main.entity.User;
import com.springcloud.main.service.UserService;

@RestController
@RefreshScope
public class UserController {
	
	private final Logger LOG = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoanClient loanClient;

	@RequestMapping("/")
	public String welcome() {
		return "Welcome";
	}

	@GetMapping("/loans/{userId}")
	public List<Loan> getUserLoans(@PathVariable(value="userId") Long userId) {
		LOG.info("Fetch User Loans...."+userId);
		return loanClient.getUserLoans(userId);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public User addProduct(@RequestBody User user) {
		return userService.addUser(user);
	}

	@RequestMapping("/detail/{id}")     
	public User getUserByUserId(@PathVariable Integer id) {
		LOG.info("Sleuth logger started..");
		return userService.getUserByUserId(id);
	}

}

