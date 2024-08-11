package com.example.demo.service;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class AppService {
	
	@ServiceActivator(inputChannel = "integration.gateway.channel")
	public void processMessage(Message<String> message) {
		
		System.out.println("inside service activator");
		System.out.println(message);
		
		MessageChannel replyChannel = (MessageChannel)message.getHeaders().getReplyChannel();
		MessageBuilder.fromMessage(message);
		Message<String> newMessage = MessageBuilder.withPayload(" Hello, This is Loan :"+message.getPayload()).build();
		
		System.out.println(newMessage);
		
		replyChannel.send(newMessage);
	}

}
