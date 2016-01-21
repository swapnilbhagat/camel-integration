package com.camelintegration.sample;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleConsumer implements Processor
{
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleConsumer.class);

	@Override
	public void process(Exchange exchange) throws Exception
	{
		Object body = exchange.getIn().getBody();
		LOGGER.info("Received message :- {}", body);
	}
}
