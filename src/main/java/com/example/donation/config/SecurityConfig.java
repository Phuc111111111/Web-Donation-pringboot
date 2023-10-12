package com.example.donation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.donation.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	UserService userService;

	//Bean này được sử dụng để định cấu hình `requestMatchers()` của 
	//Spring Security theo cách nhận biết được ánh xạ Spring MVC
	@Bean
	MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
	    return new MvcRequestMatcher.Builder(introspector);
	}
	@Bean
	 BCryptPasswordEncoder getBCryptPasswordEncode() {
		return new BCryptPasswordEncoder();
	}
//	có tác vụ cung cấp xác thực (xác thực) cho hệ thống bằng cách kiểm tra thông tin đăng nhập 
//	(tên người dùng và mật khẩu hoặc các phương thức khác) 
//	và cung cấp một đối tượng `Authentication` chứa thông tin về người dùng đã đăng nhập
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() { 
	        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
	        auth.setUserDetailsService(userService); //set the custom user details service
	        auth.setPasswordEncoder(getBCryptPasswordEncode()); //set the password encoder - noop
	        return auth;
	    }

	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {
			http.authorizeHttpRequests(configurer -> configurer
					.requestMatchers(mvc.pattern("/admin1/assets/**")).permitAll()
					.requestMatchers(mvc.pattern("/user/assets/**")).permitAll()
					.requestMatchers(mvc.pattern("/home")).permitAll()
					.requestMatchers(mvc.pattern("/login")).permitAll()
					.requestMatchers(mvc.pattern("/user/**")).hasRole("USER")
					.requestMatchers(mvc.pattern("/admin/**")).hasRole("ADMIN").anyRequest().authenticated())
			 .formLogin(form ->
             	form
                     .loginPage("/login")      //// page login
                     .loginProcessingUrl("/j_spring_security_check") // url action form when user login
                     .defaultSuccessUrl("/home") //  defaultSuccessUrl, when user login successfull, user director to url
                     .failureUrl("/login?error=true")
                     .usernameParameter("email")
                     .passwordParameter("password")
                     .permitAll()
					 )
			 .sessionManagement(session ->
             	session
             	 
                     .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                     .invalidSessionUrl("/logout")        // set Url when session invalid
                     .maximumSessions(1)
                     .maxSessionsPreventsLogin(false)     // second login will cause the first to be invalidated
					 );
//			 .logout(logout -> logout        // add logout support for default url "/logout"
//					 .logoutUrl("/logout")
//					 .logoutSuccessUrl("/login")   // it means: permitAll access to "/" too
//                     .deleteCookies("JSESSIONID")
//                     .invalidateHttpSession(true) 	// delete Cookies and invalidate Session when logout
//             );
	        return http.build();
	    }
		
	  
}
