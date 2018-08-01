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
package sample.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.MapPropertySource;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author jitendra on 3/3/16.
 */
@EnableRedisHttpSession
public class SessionConfig implements BeanClassLoaderAware {

	private ClassLoader loader;

	@Bean
	public RedisSerializer<Object> springSessionDefaultRedisSerializer() {
		return new GenericJackson2JsonRedisSerializer(objectMapper());
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
		return new JedisConnectionFactory(getClusterConfiguration());
	}

	/*@Bean
	public LettuceConnectionFactory connectionFactory() {
		return new LettuceConnectionFactory("192.168.0.7", 7001);
	}*/

	/**
	 * Customized {@link ObjectMapper} to add mix-in for class that doesn't have
	 * default constructors
	 *
	 * @return the {@link ObjectMapper} to use
	 */
	ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModules(SecurityJackson2Modules.getModules(this.loader));
		return mapper;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.beans.factory.BeanClassLoaderAware#setBeanClassLoader
	 * (java.lang .ClassLoader)
	 */
	public void setBeanClassLoader(ClassLoader classLoader) {
		this.loader = classLoader;
	}
}
