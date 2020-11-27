package com.springcloud.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.springcloud.main.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{
		Loan findByLoanNumber(Long loanNumber);
		@Query(value = "SELECT l.* FROM loan l WHERE l.user_id= :userId", nativeQuery = true)
		List<Loan> findByUserId(@Param("userId") Long userId);
}
