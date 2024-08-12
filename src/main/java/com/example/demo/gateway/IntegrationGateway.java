package com.example.demo.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import com.example.demo.model.LoanInfo;

@MessagingGateway
public interface IntegrationGateway {

	@Gateway(requestChannel = "integration.gateway.channel")
	public LoanInfo sendMessage (LoanInfo message);
	
	
	@Gateway(requestChannel = "router.channel")
	public <T> void process (T message);
	
	@Gateway(requestChannel = "router.borrowerRecipient.channel")
	public <T> void processBorrowers (T message);
}
