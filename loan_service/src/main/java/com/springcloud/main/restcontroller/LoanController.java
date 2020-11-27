package com.springcloud.main.restcontroller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcloud.main.entity.Loan;
import com.springcloud.main.entity.User;
import com.springcloud.main.service.LoanService;

@RestController
public class LoanController {

	private final Logger LOG = Logger.getLogger(this.getClass().getName());

	@Autowired
	private LoanService loanService;

	@RequestMapping("/")
	public String welcome() {
		return "Hello from Loan Microservice!!!!";
	}

	@GetMapping(value = "/user/{userId}")
	public List<Loan> getUserLoans(@PathVariable(value = "userId") Long userId) {
		LOG.info(" Fetching loans for User : " + userId);
		return loanService.getUserLoans(userId);

	}

	@PostMapping("/apply")
	public Loan addInventory(@RequestBody Loan loan, @RequestParam("userId") Long userId) {
		User u = new User();
		u.setId(userId);
		loan.setUser(u);
		return loanService.addLoan(loan);
	}

	@PutMapping("/status/{loanNumber}")
	public Loan updateStatus(@PathVariable Long loanNumber, @RequestParam("value") String status) {
		LOG.info(" Updating loans for loanNumber : " + loanNumber);
		return loanService.updateLoanStatus(loanNumber, status);
	}


}
