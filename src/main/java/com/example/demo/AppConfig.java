package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.router.RecipientListRouter;
import org.springframework.integration.transformer.HeaderEnricher;
import org.springframework.integration.transformer.MapToObjectTransformer;
import org.springframework.integration.transformer.ObjectToMapTransformer;
import org.springframework.integration.transformer.support.HeaderValueMessageProcessor;
import org.springframework.integration.transformer.support.StaticHeaderValueMessageProcessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import com.example.demo.model.Borrower;
import com.example.demo.model.LoanInfo;

@Configuration

/*Multiple configuration requirement only
@EnableIntegration
@IntegrationComponentScan
*/
public class AppConfig {
	
	// Channel
	@Bean
	public MessageChannel directChannel() {
		
		return new DirectChannel();
	}
	
	
	//Recipent List Type Router
	@Bean
	@ServiceActivator(inputChannel = "router.borrowerRecipient.channel")
	public RecipientListRouter recipientListRouter() {
		RecipientListRouter router = new RecipientListRouter();
		router.addRecipient("borrower1.channel");
		router.addRecipient("borrower2.channel");

		
		return router;
	}
	
	//PayLoad Type Router
	@Bean
	@ServiceActivator(inputChannel = "router.channel")
	public PayloadTypeRouter router() {
		PayloadTypeRouter router = new PayloadTypeRouter();
		router.setChannelMapping(LoanInfo.class.getName(),"loanInfo.channel");
		router.setChannelMapping(Borrower.class.getName(), "borrower.channel");
		
		return router;
	}
	

	// Transformer And Header Enricher for Loan
	@Bean
	@Transformer(inputChannel = "integration.gateway.channel", outputChannel = "integration.gateway.process.headerEnricher.objectToMap.channel")
	public ObjectToMapTransformer objectToMapTransformer() {
		return new ObjectToMapTransformer();
	}
	
	@Bean
	@Transformer(inputChannel = "integration.gateway.process.headerEnricher.objectToMap.channel", outputChannel = "integration.gateway.process.objectToMap.channel")
	public HeaderEnricher headerEnricher() {
		Map<String,HeaderValueMessageProcessor<String>> headerToAdd = new HashMap<String,HeaderValueMessageProcessor<String>>();
		headerToAdd.put("Loan header 1", new StaticHeaderValueMessageProcessor<String>("Test Loan Header 1"));
		headerToAdd.put("Loan header 2", new StaticHeaderValueMessageProcessor<String>("Test Loan Header 2"));

		
		HeaderEnricher enricher = new HeaderEnricher(headerToAdd);
		return enricher;
	}
	
	@Bean
	@Transformer(inputChannel = "integration.gateway.objectToMap.channel", outputChannel = "integration.gateway.loanInfo.channel")
	public MapToObjectTransformer mapToObjectTransformer() {
		return new MapToObjectTransformer(LoanInfo.class);
	}
	
	
	// Transformer And Header Enricher for Borrower
		@Bean
		@Transformer(inputChannel = "integration.gateway.borrower.channel", outputChannel = "integration.gateway.borrower.process.headerEnricher.objectToMap.channel")
		public ObjectToMapTransformer objectToMapTransformerBorrower() {
			return new ObjectToMapTransformer();
		}
		
		@Bean
		@Transformer(inputChannel = "integration.gateway.borrower.process.headerEnricher.objectToMap.channel", outputChannel = "integration.gateway.borrower.process.objectToMap.channel")
		public HeaderEnricher headerEnricherForBorrower() {
			Map<String,HeaderValueMessageProcessor<String>> headerToAdd = new HashMap<String,HeaderValueMessageProcessor<String>>();
			headerToAdd.put("Borrower header 1", new StaticHeaderValueMessageProcessor<String>("Test Borrower Header 1"));
			headerToAdd.put("Borrower header 2", new StaticHeaderValueMessageProcessor<String>("Test Borrower Header 2"));

			
			HeaderEnricher enricher = new HeaderEnricher(headerToAdd);
			return enricher;
		}
		
		@Bean
		@Transformer(inputChannel = "integration.gateway.borrower.objectToMap.channel", outputChannel = "integration.gateway.borrower.channel")
		public MapToObjectTransformer mapToObjectTransformerForBorrower() {
			return new MapToObjectTransformer(Borrower.class);
		}

	
}
