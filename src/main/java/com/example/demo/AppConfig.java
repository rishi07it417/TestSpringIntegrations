package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.MapToObjectTransformer;
import org.springframework.integration.transformer.ObjectToMapTransformer;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.MessageChannel;

import com.example.demo.model.LoanInfo;

@Configuration

/*Multiple configuration requirement only
@EnableIntegration
@IntegrationComponentScan
*/
public class AppConfig {
	
	@Bean
	public MessageChannel directChannel() {
		
		return new DirectChannel();
	}
	


	@Bean
	@Transformer(inputChannel = "integration.gateway.channel", outputChannel = "integration.gateway.process.headerEnricher.objectToMap.channel")
	public ObjectToMapTransformer objectToMapTransformer() {
		return new ObjectToMapTransformer();
	}
	
	@Bean
	@Transformer(inputChannel = "integration.gateway.process.headerEnricher.objectToMap.channel", outputChannel = "integration.gateway.process.objectToMap.channel")
	public HeaderEnricher headerEnricher() {
		Map<String,HeaderValueMessageProcessor<String>> headerToAdd = new HashMap<String,HeaderValueMessageProcessor<String>>();
		headerToAdd.put("header 1", new StaticHeaderValueMessageProcessor<String>("Test Header 1"));
		headerToAdd.put("header 2", new StaticHeaderValueMessageProcessor<String>("Test Header 2"));

		
		HeaderEnricher enricher = new HeaderEnricher(headerToAdd);
		return enricher;
	}
	
	@Bean
	@Transformer(inputChannel = "integration.gateway.objectToMap.channel", outputChannel = "integration.gateway.loanInfo.channel")
	public MapToObjectTransformer mapToObjectTransformer() {
		return new MapToObjectTransformer(LoanInfo.class);
	}

	
}
