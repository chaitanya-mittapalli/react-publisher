package com.chaitu.reactpublisher;

import org.springframework.cloud.gcp.pubsub.support.converter.JacksonPubSubMessageConverter;
import org.springframework.cloud.gcp.pubsub.support.converter.PubSubMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class OuboutConfiguration {

	@Bean
	public PubSubMessageConverter pubSubMessageConverter() {
		return new JacksonPubSubMessageConverter(new ObjectMapper());
	}
}
