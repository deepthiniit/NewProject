package com.niit.ProAngBe;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.UserDao;
import com.niit.Model.User;


public class UserTest {
public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext config=new AnnotationConfigApplicationContext();
		config.scan("com.niit.*");
		config.refresh();
		User user=(User)config.getBean("user");
		
		UserDao userDao=(UserDao)config.getBean("userDao");
		
		user.setFirstname("HARISH");
		user.setRole("ROLE_ADMIN");
		user.setLastname("BANGALORE");
		user.setEmail("harish@gmail.com");
		user.setOnline(true);
        user.setPassword("123");
        user.setPhonenumber("9845012345");
        user.setUsername("uname");
        userDao.registerUser(user);
        
        
		
		
		
	}
}
