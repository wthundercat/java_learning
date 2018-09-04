package com.wai.hibernate;



import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wai.dao.CRUDInterface;

import static org.junit.Assert.assertNull;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
public class AppTestSpring 
    
{
   public static CRUDInterface personDaoImpl;

	   @BeforeClass
	   public static void setUp() throws Exception
	   {
		   ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		   personDaoImpl = context.getBean(CRUDInterface.class);

	   }
	   
	   @Test
	   public void testSave() {
		   personDaoImpl.save();
	   }
	   
	  

	 
   
}
