package cn.milo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by tl on 17/2/16.
 */
@Component
public class RedisTemplateUtil {

    private static RedisTemplate redisTemplate;

    @Autowired
    public RedisTemplateUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public static RedisTemplate getRedisTemplate(){
        return redisTemplate;
    }


    public static void set(String key, Object value) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);

        //BoundValueOperations的理解对保存的值做一些细微的操作
//        BoundValueOperations boundValueOperations = redisTemplate.boundValueOps(key);
    }

    public static Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static void setList(String key, List<?> value) {
        ListOperations listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, value);
    }

    public static Object getList(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    public static void setSet(String key, Set<?> value) {
        SetOperations setOperations = redisTemplate.opsForSet();
        setOperations.add(key, value);
    }

    public static Object getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }


    public static void setHash(String key, Map<String, ?> value) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        hashOperations.putAll(key, value);
    }

    public static Object getHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }


    public static void delete(String key) {
        redisTemplate.delete(key);
    }

//    public void clearAll(){
//        redisTemplate.multi();
//    }
}
