package com.dev.blackmango.common.config;

import com.dev.blackmango.model.entity.QBoard;
import com.dev.blackmango.model.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableJpaRepositories(basePackages = {"com.dev.blackmango.repository",
    "com.dev.blackmango.repository.query"})
public class AppConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public JPAQueryFactory jpaQueryFactory(EntityManager em) {
    return new JPAQueryFactory(em);
  }

  @Bean
  public QBoard board() {
    return new QBoard("board");
  }
  @Bean
  public QUser user() {
    return new QUser("user");
  }
}
