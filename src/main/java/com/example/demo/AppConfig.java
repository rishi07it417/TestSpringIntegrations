package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;

@Configuration

/*Multiple configuration requirement only
@EnableIntegration
@IntegrationComponentScan
*/
public class AppConfig {
	
	@Bean
	public MessageChannel receiveChannel() {
		
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel replyChannel() {
		
		return new DirectChannel();
	}

}
