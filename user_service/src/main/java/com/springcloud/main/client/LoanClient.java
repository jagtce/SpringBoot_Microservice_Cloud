package com.springcloud.main.client;

import java.util.Collections;
import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.springcloud.main.client.LoanClient.LoanClientFallback;
import com.springcloud.main.entity.Loan;

@FeignClient(name="loan-service",fallback=LoanClientFallback.class)
public interface LoanClient {
	@GetMapping( value="/user/{userId}")
	public List<Loan> getUserLoans(@PathVariable(value="userId") Long userId);
	
	@Component
    class LoanClientFallback implements LoanClient {

        @Override
        public List<Loan> getUserLoans(Long userId) {
        	System.out.println("############# LoanClientFallback called#######################");
    		return Collections.emptyList();
        }
    }
}
