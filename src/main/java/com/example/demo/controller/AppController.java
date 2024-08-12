package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.transformer.ObjectToMapTransformer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.gateway.IntegrationGateway;
import com.example.demo.model.Borrower;
import com.example.demo.model.LoanInfo;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired
	IntegrationGateway interationGateway;
	
	@PostMapping("/processLoan")
	public LoanInfo processLoan(@RequestBody LoanInfo loan) {
		//return interationGateway.sendMessage(loan);
		
		interationGateway.process(loan);
		
		return loan;

	}
	
	@PostMapping("/processBorrower")
	public String processLoan(@RequestBody Borrower borrower) {
		System.out.println(borrower.toString());
		
		interationGateway.process(borrower);

		
		return "";
	}
	
	
}
