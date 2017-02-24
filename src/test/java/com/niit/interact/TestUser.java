package com.niit.interact;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.interact.dao.UserDAO;
import com.niit.interact.model.User;

public class TestUser {
	
	@Autowired
	static UserDAO userDAO;
	
	@Autowired
	static User user;
	
	@Autowired
	static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init(){
		context=new AnnotationConfigApplicationContext();
		context.scan("com.niit.interact");
		context.refresh();
		
		user=(User)context.getBean("user");
		userDAO=(UserDAO)context.getBean("userDAOImpl");
	}

	@Test
	public void saveTestCase()
	{
		user.setName("Teja");
	    user.setMobile("7676767676");
		user.setEmail("teja@gmail.com");
		user.setPassword("teja");
	    user.setRole("ROLE_ADMIN");
	    user.setUsername("teja");
	    user.setIs_online('Y');
	    user.setStatus('Y');
		
	    Assert.assertEquals("save Test Case", true, userDAO.saveOrUpdate(user));
	}

}
