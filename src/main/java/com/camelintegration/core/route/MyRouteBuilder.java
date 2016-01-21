package com.camelintegration.core.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder
{
	@Override
	public void configure() throws Exception
	{
		from("activemq:SAMPLE_ROUTE").to("bean:sampleConsumer");
	}
}
