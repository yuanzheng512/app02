package com.briup.apps.app02;

import com.briup.apps.app02.bean.User;
import com.briup.apps.app02.dao.UserMapper;
import com.briup.apps.app02.util.RedisUtil;
import com.briup.apps.app02.util.StringRedisUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

/**
 * Created by ASUS on 2018/4/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StringRedisUtils stringredisutils;
    @Test
    public void testSet(){

        try {
            List<User> lis = userMapper.findAll();
            ObjectMapper mapper = new ObjectMapper();
            stringredisutils.set("key",mapper.writeValueAsString(lis));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testGet(){
      String json =  stringredisutils.get("key");
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<User> beanList = mapper.readValue(json, new TypeReference<List<User>>() {});
            System.out.print(beanList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(json);
    }
}
