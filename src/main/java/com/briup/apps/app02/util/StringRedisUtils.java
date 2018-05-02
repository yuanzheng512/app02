package com.briup.apps.app02.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by ASUS on 2018/4/28.
 */
@Component
public class StringRedisUtils {
    @Autowired
    private StringRedisTemplate stringredistemplate;


    public String set(String key,String value){
       stringredistemplate.opsForValue().set(key,value);

       return "SUCCESS";
    }
    public String get(String key){
        return stringredistemplate.opsForValue().get(key);
    }
}
