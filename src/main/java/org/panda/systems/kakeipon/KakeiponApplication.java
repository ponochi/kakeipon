package org.panda.systems.kakeipon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration( proxyBeanMethods = false )
// @Configuration
@SpringBootApplication
public class KakeiponApplication {
  public static void main( String[] args ) {
    SpringApplication.run( KakeiponApplication.class, args );
  }
}
