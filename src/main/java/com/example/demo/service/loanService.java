package com.example.demo.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class loanService {
	
	@ServiceActivator(inputChannel = "loanInfo.channel", outputChannel = "integration.gateway.channel")
	public Message<?> getLoanMessage(Message<?> message) {
		System.out.println("inside getLoanMessage service activator ::::");
		System.out.println(message);
		
		System.out.println("inside getLoanMessage service activator ::PAYLOAD::");
		System.out.println(message.getPayload());
		
		return message;

	}

}
