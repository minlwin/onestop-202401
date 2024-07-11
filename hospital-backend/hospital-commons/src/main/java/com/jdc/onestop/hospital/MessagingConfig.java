package com.jdc.onestop.hospital;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("messaging")
public class MessagingConfig {
	
	@Bean
	Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
		var converter = new Jackson2JsonMessageConverter();
		return converter;
	}
		
	@Configuration
	@Profile("doctor-section")
	public static class DoctorSection {
		
		private static final String SECTION_CHANGE = "DOCTOR_SECTION";

		@Bean
		DirectExchange sectionChange() {
			return ExchangeBuilder.directExchange(SECTION_CHANGE).build();
		}
		
		@Bean
		Queue newSectionQueue() {
			return QueueBuilder.durable().build();
		}
		
		@Bean
		Binding newSectionBinding() {
			return BindingBuilder.bind(newSectionQueue()).to(sectionChange()).with(DoctorSectionChangeAction.Create);
		}
		
		@Bean
		Queue changeSectionQueue() {
			return QueueBuilder.durable().build();
		}

		@Bean
		Binding changeSectionBinding() {
			return BindingBuilder.bind(changeSectionQueue()).to(sectionChange()).with(DoctorSectionChangeAction.Update);
		}

		@Bean
		Queue deleteSectionQueue() {
			return QueueBuilder.durable().build();
		}

		@Bean
		Binding deleteSectionBinding() {
			return BindingBuilder.bind(deleteSectionQueue()).to(sectionChange()).with(DoctorSectionChangeAction.Update);
		}
	}
	
	public enum DoctorSectionChangeAction {
		Create, Update, Delete
	}
}
