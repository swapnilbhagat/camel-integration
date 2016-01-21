package com.camelintegration.sample;

import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleProducer
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleProducer.class);

	private ProducerTemplate producerTemplate;

	@Autowired
	public void setProducerTemplate(ProducerTemplate producerTemplate)
	{
		this.producerTemplate = producerTemplate;
	}

	public void sendMessage()
	{
		String messageBody = "SAMPLE MESSAGE BODY";
		LOGGER.info("Sending message :- {}", messageBody);
		producerTemplate.sendBody("activemq:SAMPLE_ROUTE", messageBody);
	}
}
