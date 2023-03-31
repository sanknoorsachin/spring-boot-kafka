package com.example.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.example.kafka.model.UserDetails;

@Configuration
public class KafkaConfiguration {

	@Bean
	public KafkaAdmin admin() {
		return new KafkaAdmin(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"));
	}

	@Bean
	public NewTopic topic1() {
		return TopicBuilder.name("kafka_topic")
				.partitions(1)
				.replicas(1)
				.build();
	}
	
	@Bean
	public NewTopic topic2() {
		return TopicBuilder.name("Kafka_Example_json")
				.partitions(1)
				.replicas(1)
				.build();
	}

	
	  @Bean public ProducerFactory<String, UserDetails> producerFactory() {
	  Map<String, Object> config = new HashMap<>();
	  
	  config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
	  config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	  StringSerializer.class);
	  config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,JsonSerializer.class); 
	  config.put(JsonSerializer.ADD_TYPE_INFO_HEADERS,false);
	  
	  return new DefaultKafkaProducerFactory<>(config); 
	  }
	  
	  @Bean public KafkaTemplate<String, UserDetails> kafkaTemplate() {
		  return new KafkaTemplate<>(producerFactory()); 
		  }
	 
}
