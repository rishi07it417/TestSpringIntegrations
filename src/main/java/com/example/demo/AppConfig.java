package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.transformer.MapToObjectTransformer;
import org.springframework.integration.transformer.ObjectToMapTransformer;
import org.springframework.messaging.MessageChannel;

import com.example.demo.model.LoanInfo;

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

	@Bean
	@Transformer(inputChannel = "integration.gateway.channel", outputChannel = "integration.gateway.process.objectToMap.channel")
	public ObjectToMapTransformer objectToMapTransformer() {
		return new ObjectToMapTransformer();
	}
	
	@Bean
	@Transformer(inputChannel = "integration.gateway.objectToMap.channel", outputChannel = "integration.gateway.loanInfo.channel")
	public MapToObjectTransformer mapToObjectTransformer() {
		return new MapToObjectTransformer(LoanInfo.class);
	}

	
}
