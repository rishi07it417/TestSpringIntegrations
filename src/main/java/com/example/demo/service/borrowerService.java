package com.example.demo.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class borrowerService {
	
	@ServiceActivator(inputChannel = "borrower.channel", outputChannel = "integration.gateway.borrower.channel")
	public Message<?> getBorrowerMessage(Message<?> message) {
		System.out.println("inside getBorrowerMessage service activator ::::");
		System.out.println(message);

		System.out.println("inside getBorrowerMessage service activator ::PAYLOAD::");
		System.out.println(message.getPayload());
		
		return message;
	}
	
	
	@ServiceActivator(inputChannel = "integration.gateway.borrower.process.objectToMap.channel", outputChannel = "integration.gateway.borrower.objectToMap.channel")
	public Message<?> processMapMessage(Message<?> message) {
		System.out.println("inside processMapMessage service for Borrower activator:::::");
		System.out.println(message);

		
		System.out.println("inside processMapMessage service  for Borrower activator:::PAYLOAD::");

		System.out.println(message.getPayload());
		
		
		return message;
	}
	
	@ServiceActivator(inputChannel = "integration.gateway.borrower.channel")
	public void getObjectMessage(Message<?> message) {
		System.out.println("inside getObjectMessage service activator for borrower::::");
		System.out.println(message);

		System.out.println("inside getObjectMessage service activator for borrower::PAYLOAD::");
		System.out.println(message.getPayload());
		
		/*MessageChannel replyChannel = (MessageChannel)message.getHeaders().getReplyChannel();
		MessageBuilder.fromMessage(message);
				
		replyChannel.send(message);*/
	}
	

}
