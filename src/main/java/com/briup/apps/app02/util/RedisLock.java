package com.briup.apps.app02.util;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**redis 并发锁
 * Created by ASUS on 2018/5/2.
 */
@Service
public class RedisLock implements Lock{
    @Resource
    private JedisConnectionFactory factory;

    private static final String  LOCK = "LOCK";
    @Override
    public void lock() {
        if(!tryLock()){
            try {
                Thread.sleep(new Random().nextInt(10));
                lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        Jedis jedis =(Jedis)factory.getConnection().getNativeConnection();
        String value = UUID.randomUUID().toString();
         String ret = jedis.set(LOCK,value,"NX","PX",3000);
         jedis.setnx(LOCK,value);
         if(ret !=null && ret.equals("OK")){
             return true;
         }
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
