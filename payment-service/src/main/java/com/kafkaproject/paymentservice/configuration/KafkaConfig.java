package com.kafkaproject.paymentservice.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import org.apache.kafka.common.serialization.StringSerializer;
import com.kafkaproject.paymentservice.entity.Payment;

import jakarta.annotation.PreDestroy;

@Configuration
public class KafkaConfig {

//#spring.kafka.consumer.bootstrap-servers=localhost:9092
//#spring.kafka.consumer.group-id=payment
//#spring.kafka.consumer.auto-offset-reset=earliest
//#spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
//#spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
//#spring.kafka.consumer.properties.spring.json.trusted.packages=*
	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		// Configure the necessary consumer properties
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "payment");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put("spring.json.trusted.packages", "*");
		// Add any additional properties

		return new DefaultKafkaConsumerFactory<>(props);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		// Set any additional properties for the listener container factory

		return factory;
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name("payment").build();
	}


	@Bean
	public ProducerFactory<String, Payment> producerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(configProps);
	}

	@Bean
	public KafkaTemplate<String, Payment> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

//	@PreDestroy
//	public void closeProducer() {
//		if (kafkaTemplate() != null && kafkaTemplate().getProducerFactory() instanceof DefaultKafkaProducerFactory) {
//			Producer<String, Payment> producer = ((DefaultKafkaProducerFactory<String, Payment>) kafkaTemplate()
//					.getProducerFactory()).createProducer();
//			producer.close();
//		}
//	}
}