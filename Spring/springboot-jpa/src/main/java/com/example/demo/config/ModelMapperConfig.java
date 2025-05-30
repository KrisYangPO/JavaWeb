package com.example.demo.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Springboot 完成前，會先執行此配置，提供預設功能。
public class ModelMapperConfig {

	// @Bean 讓 SpringBoot 會自行管理 new ModelMapper() 物件，
	// 其他程式可以透過 @autoWired 來取得這個 @bean ModelMapper 的物件實體。
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	/* @Bean & @Autowired:	
	 * 因為 @Bean 標籤，可以讓其他的物件或是系統，透過 @Autoweired 標籤使用這個"方法"。
	 * 
	 * 另外像是 @Component 與他的子繼承 @Service, @Repository 都有與 @Bean 一致的功能，
	 * 因此在其他系統物件中，想要呼叫 Repository 物件實體時，不用像以前：
	 * RoomRepository roomRepository = new RoomRepositoryImpl();
	 * 可以直接寫：
	 * 		@Autowired 
	 * 		private RoomRepository roomRepository; 
	 * 
	 * 這樣就可以呼叫 RoomRepository 所定義的功能。
	 * 
	 */ 
}
