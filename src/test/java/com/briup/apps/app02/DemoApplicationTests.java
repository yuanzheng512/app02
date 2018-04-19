package com.briup.apps.app02;

import com.briup.apps.app02.bean.User;
import com.briup.apps.app02.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class DemoApplicationTests {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void contextLoads() {
	}
	@Test
	public void testUserMapper(){
		try {
		List<User> lis =	userMapper.findAll();
		System.out.print(lis.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testAdd1(){
		User user = new User();
//		user.setName("bobo");
		user.setGender("男");
//		user.setAge(12L);
		user.setDate(new Date());
		userMapper.add(user);
		System.out.println("id ==");
		System.out.println(user.getMid());
	}

	@Test
	public  void testAddBatch(){
		List<User> list = new ArrayList<User>();
		for(int i=0; i<10;i++){
			User user = new User();
			user.setName("xiaoming"+i);
			user.setGender("男");
			list.add(user);
		}
		userMapper.addBatch(list);
	}

}
