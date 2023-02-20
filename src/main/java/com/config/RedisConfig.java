/*
package com.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

*/
/**
 * @Classname: RedisConfig
 * @Date: 5/5/2021 11:38 下午
 * @Author: garlam
 * @Description:
 *//*



@Configuration
public class RedisConfig {
    private static Logger log = LogManager.getLogger(RedisConfig.class.getName());

    @Bean
    @SuppressWarnings("unchecked")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        // 配置连接工厂
        template.setConnectionFactory(factory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        Jackson2JsonRedisSerializer jacksonSeial = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper om = new ObjectMapper();
        // 指定要序列化的域，field,get和set,以及修饰符范围，ANY是都有包括private和public
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        // om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);   //过期，用下面的方法来代替
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        jacksonSeial.setObjectMapper(om);

        // value序列化方式采用jackson
        template.setValueSerializer(jacksonSeial);
        // key采用String的序列化方式
        template.setKeySerializer(new StringRedisSerializer());

        // 对hash的key采用String的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        // 对hash的value采用jackson的序列化方式
        template.setHashValueSerializer(jacksonSeial);
        template.afterPropertiesSet();

        return template;

        // 我们为了自己开发方便，一般直接使用 <String, Object>
        //RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        //template.setConnectionFactory(factory);
        // Json序列化配置
        //Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //ObjectMapper om = new ObjectMapper();
        //om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        //jackson2JsonRedisSerializer.setObjectMapper(om);
        // String 的序列化
        //StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        //template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        //template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        //template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        //template.setHashValueSerializer(jackson2JsonRedisSerializer);
        //template.afterPropertiesSet();
        //return template;
    }

}

*/
