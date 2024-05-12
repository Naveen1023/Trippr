package com.cosmos.trippr.utils.cache.redis;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@PropertySource("file:src/main/resources/application.properties")
@EnableCaching
@Log4j2
public class RedisConfig {

//  @Autowired
//  private Environment env;
//
//  @Value("${spring.redis.url}")
//  private static String redisHost;
//  @Value("${spring.redis.port}")
//  private static int redisPort;

//  @Bean
//  public JedisConnectionFactory jedisConnectionFactory() {
//    log.info("HOST : "+ redisHost);
//    log.info("PORT : "+ redisPort );
//
//    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//    redisStandaloneConfiguration.setHostName("localhost");
//    redisStandaloneConfiguration.setPort(6379);
//    return new JedisConnectionFactory(redisStandaloneConfiguration);
//  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    template.setKeySerializer(new StringRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }
}
