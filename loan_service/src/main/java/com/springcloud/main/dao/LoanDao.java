package com.springcloud.main.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springcloud.main.entity.Loan;
import com.springcloud.main.repository.LoanRepository;

@Repository("loanDao")
public class LoanDao {
	
	@Autowired
	private LoanRepository loanRepository;

	public List<Loan> getUserLoans(Long userId) {
		// TODO Auto-generated method stub
		return loanRepository.findByUserId(userId);
	}

	public Loan addLoan(Loan ie) {
		// TODO Auto-generated method stub
		return loanRepository.save(ie);
	}
	
	public Loan updateLoanStatus(Long loanNumber,String status) {
		Loan loan = loanRepository.findById(loanNumber).get();
		loan.setLoanStatus(status);
		return loanRepository.save(loan);
	}


}
