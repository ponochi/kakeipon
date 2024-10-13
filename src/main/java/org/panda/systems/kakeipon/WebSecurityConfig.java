package org.panda.systems.kakeipon;

import org.panda.systems.kakeipon.domain.service.users.UsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@ComponentScan
@Configuration
@EnableWebSecurity
@EnableMethodSecurity // (prePostEnabled = true)
@Import(AppConfig.class)
public class WebSecurityConfig {

  @Autowired
  private DataSource dataSource;
  @Autowired
  private UsersDetailService usersDetailService;

  @Bean
  PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories
        .createDelegatingPasswordEncoder();
  }

//  private UserDetails makeUser(String username, String password, String role) {
//    return Users
//        .withUsername(username)
//        .password(new BCryptPasswordEncoder().encode(password))
//        .roles(role)
//        .disabled(false)
//        .build();
//  }

  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity http) throws Exception {

    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authz -> authz
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations().toString()).permitAll()
            .requestMatchers("/resources/**", "/login", "/").permitAll()
            .anyRequest().authenticated()
        ).formLogin(login -> login
            .loginPage("/login")
            .loginProcessingUrl("/sign_in")
//            .usernameParameter("username")
//            .passwordParameter("password")
            .defaultSuccessUrl("/top", true)
            .failureUrl("/login?error=true")
        );

    return http.build();
  }
}
