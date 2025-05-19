package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Springboot 完成前，會先執行此配置。
public class ModelMapperConfig {

	// @Bean 讓 SpringBoot 會自行建立此物件，並來管理
	// 其他程式可以透過 @autoWired 來取得這個 @bean 物件實體。
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
