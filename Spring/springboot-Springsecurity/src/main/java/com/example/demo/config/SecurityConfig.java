package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth // 設定授權
				.requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll() // "/login" 不用授權
				.requestMatchers("/admin/**").hasRole("ADMIN") // 如果路徑上有 admin，使用者就必須要有ADMIN身份
				.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN") // 如果路徑上有 User，使用者就需要有 User 或 ADMIN 身份。
				.anyRequest().authenticated() // 其餘都要授權
		)
		// ===== 表單登入設定 =====
		.formLogin(form -> form // 自訂表單授權頁
				.loginPage("/login")
				.permitAll() // 允許所有人存取登入頁
		)
		// ===== 登出設定 =====
		.logout(logout -> logout
				.logoutSuccessUrl("/login?logout=true")
				// 因為 logout 預設需要用 POST
				// 若一定要使用 GET，可以透過 logoutRequestMatcher 來修改為 GET (非官方建立)
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
				.permitAll()
		)
		// ===== 例外處理（權限不足）設定 =====
		.exceptionHandling(exception -> exception
				.accessDeniedPage("/accessDenied")); // 權限不足時導向 /accessDenied 頁面
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		// 建立使用者-InMemory版本
		UserDetails user = User.builder()
				.username("user")
				.password(passwordEncoder.encode("1234"))
				.roles("USER")
				.build();
		
		UserDetails admin = User.builder()
				.username("admin")
				.password(passwordEncoder.encode("5678"))
				.roles("USER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user, admin);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // 建立加密元件(已加鹽)
	}	
}