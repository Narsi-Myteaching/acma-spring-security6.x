/**
 * 
 */
package com.acma.properties.events;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.acma.properties.models.PropertyLead;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Component
@Slf4j
public class LeadsProducer {

	@Value("${acma.properties.lead.topic}")
	private String leadsTopic;
	
	@Value("${acma.properties.lead.partitions}")
	private String partitions;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public boolean produceLead(PropertyLead propertyLead){
		boolean isLeadSent = false;
		log.info("Leads Topic is "+leadsTopic);
		log.info("No.Of Partitions currently availale "+partitions);
		try {
			String key = propertyLead.getPropertyInfo();
			log.info("Key is "+key);
			String value = objectMapper.writeValueAsString(propertyLead);
			
			CompletableFuture<SendResult<String, String>>  future = kafkaTemplate.send(leadsTopic, key, value);
			if(future.complete(future.get())) {
				isLeadSent = true;
			}
			log.info("Current status "+future.isDone());
			log.info("Current status "+future.isCompletedExceptionally());
			log.info("Current status "+future.isCancelled());
			log.info("Does Lead sent "+isLeadSent);		
			
			
		}catch (Exception e) {
			log.error("ERROR: "+e.getLocalizedMessage());
		}
		
		
		
		return isLeadSent;
	}
	
}
