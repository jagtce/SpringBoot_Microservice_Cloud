package com.springcloud.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcloud.main.dao.LoanDao;
import com.springcloud.main.entity.Loan;

@Service("inventoryService")
public class LoanService {
	
	@Autowired
	private LoanDao loanDao;

	public List<Loan> getUserLoans(Long userId) {
		// TODO Auto-generated method stub
		return loanDao.getUserLoans(userId);
	}

	public Loan addLoan(Loan ie) {
		// TODO Auto-generated method stub
		return loanDao.addLoan(ie);
	}

	public Loan updateLoanStatus(Long loanNumber,String status) {
		return loanDao.updateLoanStatus(loanNumber, status);
	}
}
