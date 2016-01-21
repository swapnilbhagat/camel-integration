package com.camelintegration.core.config;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.impl.DefaultProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.camelintegration.core.route.MyRouteBuilder;

@Configuration
public class CamelIntegrationConfiguration
{
	private static final String CAMEL_URL_MAPPING = "/camel/*";
	private static final String CAMEL_SERVLET_NAME = "CamelServlet";

	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public ServletRegistrationBean createServletRegistrationBean()
	{
		ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(),
				CAMEL_URL_MAPPING);
		registration.setName(CAMEL_SERVLET_NAME);
		return registration;
	}

	@Bean
	public SpringCamelContext createCamelContext(ApplicationContext applicationContext) throws Exception
	{
		SpringCamelContext camelContext = new SpringCamelContext(applicationContext);
		camelContext.addRoutes(createRouteBuilder());
		camelContext.start();
		return camelContext;
	}

	public ActiveMQComponent createActiveMQ()
	{
		ActiveMQComponent activeMQ = new ActiveMQComponent();
		activeMQ.setBrokerURL("${mq.brokerUrl}");
		return activeMQ;
	}

	@Bean
	public ProducerTemplate createProducerTemplate() throws Exception
	{
		DefaultProducerTemplate producerTemplate = new DefaultProducerTemplate(createCamelContext(applicationContext));
		producerTemplate.start();
		return producerTemplate;
	}

	@Bean
	public RouteBuilder createRouteBuilder()
	{
		return new MyRouteBuilder();
	}

}
