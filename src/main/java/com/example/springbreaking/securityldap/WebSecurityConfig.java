package com.example.springbreaking.securityldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * 시큐리티 + LDAP을 이용한 로그인 방식
 */
@Configuration
public class WebSecurityConfig {


    /**
     * 직접 로그인 했을 경우 - fullyAuthenticated() / authenticated() - 세션 기억
     * @param http
     * @return
     * @throws Exception
     */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .anyRequest().fullyAuthenticated()
        .and()
      .formLogin();

    return http.build();
  }

    /**
     * LDAP 인증 내장 서버 - 공식문서 차용
     * 현재 설정으로는 [id: ben, password: benspassword] 로 로그인 가능
     * @param auth
     * @throws Exception
     */
  @Autowired
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .ldapAuthentication()
        .userDnPatterns("uid={0},ou=people")
        .groupSearchBase("ou=groups")
        .contextSource()
          .url("ldap://localhost:8389/dc=springframework,dc=org")
          .and()
        .passwordCompare()
          .passwordEncoder(new BCryptPasswordEncoder())
          .passwordAttribute("userPassword");
  }

}

//@Configuration
//public class WebSecurityConfig {

//  @Bean
//  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//    return http
//      .authorizeRequests()
//      .anyRequest().authenticated()
//      .and()
//      .formLogin(Customizer.withDefaults())
//      .build();
//  }
//}
