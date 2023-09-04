package com.example.springbreaking.securityWeb;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 인증, 권한부여, 인코딩, CORS, CSRF, 세션 관리, 로그인 페이지 설정 등..
 * configuration 포함
 * 시큐리티 필터체인 등록
 */
@EnableWebSecurity
public class WebSecurityConfig {

	/**
	 * 필터 체인 등록, 기본적인 형태
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.antMatchers("/", "/home").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}

	/**
	 * 1. 입력된 정보의 사용자 정보를 로드
	 * 2. 인증 프로세서 AuthenticationManager는 UserDetailsService를 사용하여 사용자의 세부 정보를 가져온다.
	 * 3. 사용자의 세부 정보 반환 : UserDetails 객체 (username, password, granted authorities)
	 * 4. 확장 (Override) 가능
	 *
	 * 인터페이스를 구현한 클래스에서
	 *
	 *     @Override
	 *     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	 *         // 데이터베이스에서 사용자 정보 가져오기
	 *         User user = userRepository.findByUsername(username);
	 *
	 *         if (user == null) {
	 *             throw new UsernameNotFoundException("User not found");
	 *         }
	 *
	 *         // UserDetails 객체 반환
	 *         return new org.springframework.security.core.userdetails.User(
	 *             user.getUsername(),
	 *             user.getPassword(),
	 *             new ArrayList<>() // 여기에서는 간단하게 권한을 비어있는 리스트로 설정했습니다.
	 *         );
	 *     }
	 *
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user =
			 User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}