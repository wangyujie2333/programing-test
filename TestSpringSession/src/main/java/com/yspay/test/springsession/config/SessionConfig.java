/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yspay.test.springsession.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.events.SessionExpiredEvent;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;

import redis.clients.jedis.JedisShardInfo;

/**
 * @author jitendra on 3/3/16.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30, redisNamespace = "TestSpringSession")
public class SessionConfig {
	// 定制对象转换，持久化到redis之前需要把java对象转为字符串或者字节
	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return new GenericJackson2JsonRedisSerializer(objectMapper());
	}

	// 定制cookie名称和域名，输出到浏览器端的时候，可以定制名称，特别是在多个子域名情况下，需要定制域名为父域名
	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer serializer = new DefaultCookieSerializer();
		serializer.setCookieName("JSESSIONID"); // <1>
		serializer.setCookiePath("/"); // <2>
		serializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$"); // <3>
		return serializer;
	}

	@Bean
	public RedisClusterConfiguration getClusterConfiguration() {
		Map<String, Object> source = new HashMap<String, Object>();
		source.put("spring.redis.cluster.nodes",
				"192.168.0.7:7001,192.168.0.7:7000,192.168.0.7:7003,192.168.0.7:7004,192.168.0.7:7005,192.168.0.7:7006");
		source.put("spring.redis.cluster.timeout", 2000);
		source.put("spring.redis.cluster.max-redirects", 8);
		return new RedisClusterConfiguration(
				new MapPropertySource("RedisClusterConfiguration", source));
	}

	@Bean
	public JedisConnectionFactory getConnectionFactory() {
		//return new JedisConnectionFactory(getClusterConfiguration());
		return new JedisConnectionFactory(new JedisShardInfo("127.0.0.1",6379));
	}

	/**
	 * Customized {@link ObjectMapper} to add mix-in for class that doesn't have
	 * default constructors
	 *
	 * @return the {@link ObjectMapper} to use
	 */
	ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		// mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
		return mapper;
	}

	@Bean
	public ApplicationListener<SessionExpiredEvent> getSessionExpiredEventListener() {
		return new ApplicationListener<SessionExpiredEvent>() {

			@Override
			public void onApplicationEvent(SessionExpiredEvent event) {
				System.out.println(
						"Session expired! Id is " + event.getSessionId());
			}

		};
	}
}
