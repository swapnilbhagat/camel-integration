package com.camelintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.camelintegration.sample.SampleProducer;

@SpringBootApplication
public class CamelIntegrationApplication
{
	public static void main(String[] args)
	{
		ConfigurableApplicationContext applicationContext = SpringApplication.run(CamelIntegrationApplication.class, args);
		SampleProducer sampleProducer = applicationContext.getBean(SampleProducer.class);
		sampleProducer.sendMessage();
	}
}
