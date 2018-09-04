package com.wai.hibernate;



import org.hibernate.LazyInitializationException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.sf.ehcache.CacheManager;

import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
public class AppTest 
    
{
   
	
	private static SessionFactory sessionFactory = null;

	   @BeforeClass
	   public static void setUp() throws Exception
	   {
	      sessionFactory = new Configuration().configure().buildSessionFactory();

	   }
	   
	   @AfterClass
	   public static void tearDown() throws Exception
	   {
	      sessionFactory.close();
	   }

	   @Test
	   public void testSave() {
		   //Person person1 = new Person();
		   Person person1 = new Person("John", "Thundercat");
		   System.out.println(person1.getLastName());
		   person1.setFirstName("john");
		   person1.setLastName("thundercat");
		   Session session = sessionFactory.openSession();
		      session.beginTransaction();

		      session.save( person1 );
		    

		      session.getTransaction().commit();
		     	      
		      Person person2 = new Person("wai", "baaaa");
		      session.beginTransaction();

		      session.save( person2 );
		    

		      session.getTransaction().commit();
		      
		      session.close();
		   System.out.println("hello");
	   }
	   
	   @Test
	   public void testRetrieve() {
		   testSave();
		   Session session = sessionFactory.openSession();
		   session.beginTransaction();
		   Person person = session.get( Person.class, 1 );
		   session.close();
		   assertNotNull(person);
		   
		   assertEquals(1,person.getId());
		   
	   }
	   
	   @Test
	   public void testUpdate() {
		  
		   testSave();
		   Session session = sessionFactory.openSession();
		   session.beginTransaction();
		   
		   Person personB4Update = session.get(Person.class, 1);
		   String firstName = personB4Update.getFirstName();
		   assertEquals("john", firstName);
		   
		   //Updating first person
		   personB4Update.setFirstName("aquafina");
		   session.update( personB4Update );
		   session.getTransaction().commit();
		   
		   Person afterUpdate = session.get(Person.class, 1);
		   session.close();
		   assertNotEquals("john", afterUpdate.getFirstName());
		   assertEquals("aquafina", afterUpdate.getFirstName());
	   }
	   
	   @Test
	   public void testDelete() {
		   testSave();
		   Session session = sessionFactory.openSession();
		   session.beginTransaction();
		   Person p = session.get(Person.class, 1);
		   assertNotNull(p);
		   session.delete(p);
		   session.getTransaction().commit();
		   
		   Person p2 = session.get(Person.class, 1);
		   session.close();
		   assertNull(p2);
	   }
	   
	   @Test
	   public void testEmbeddedObject() {
		   	Session session = sessionFactory.openSession();
		      session.beginTransaction();
		      Person person = new Person( "James", "Bond" );

		      Address homeAddress = new Address( "Dodge street", "Omaha", "Nebraska", "68102" );
		     

		      person.setHomeAddress( homeAddress );
		      session.save( person );
		      session.getTransaction().commit();
		      session.close();
		      
	   }
	   
	   @Test
	   public void testCollection() {
		   Session session = sessionFactory.openSession();
		      session.beginTransaction();
		      Person person = new Person( "James", "Bond" );
		      Insurance insurance = new Insurance("Geico");
		      Insurance insurance2 = new Insurance("USAA");
		      person.getInsurances().add(insurance);
		      person.getInsurances().add(insurance2);
		      session.save(person);
		      session.getTransaction().commit();
		      session.close();
	   }
	   
	   @Test( expected = LazyInitializationException.class )
	   public void testLazyLoading() {
		   
		   testCollection();
		   
		   Session session = sessionFactory.openSession();
		   session.beginTransaction();
		   Person p = session.get(Person.class, 1);
		   session.getTransaction().commit();
		   session.close(); //by closing the session it will throw lazyLoadException
		   Collection<Insurance> insurance = p.getInsurances();
		   System.out.println(p.getInsurances().toString());
		   session.close();
	   }
	   
	   @Test
	   public void testEagerLoading() {
		   
		   testCollection();
		   
		   Session session = sessionFactory.openSession();
		   session.beginTransaction();
		   Person p = session.get(Person.class, 1);
		   session.getTransaction().commit();
		   session.close();
		   System.out.println(p.getInsurances());
		   
	   }
	   
	   @Test
	   public void testHibernateSecondLevelCache() {
		   testSave();
		   Session session = sessionFactory.openSession();
		   session.beginTransaction();
		   Person person = session.get( Person.class, 1 );
		   session.getTransaction().commit();
		   session.close();
		   assertNotNull(person);
		   assertEquals(1,person.getId());
		   
		   Session session2 = sessionFactory.openSession();
		   session2.beginTransaction();
		   Person person2 = session2.get( Person.class, 1 );
		   session2.getTransaction().commit();
		   session2.close();
		   
		   List<CacheManager> cacheManager = CacheManager.ALL_CACHE_MANAGERS;
		   assertEquals(1, cacheManager.size());
		   
	   }
   
}
