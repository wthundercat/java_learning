package com.wai.daoimpl;

import com.wai.dao.CRUDInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
public class PersonDaoImpl implements CRUDInterface {

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("saving");
		
	}

	public void update() {
		// TODO Auto-generated method stub
		
	}

}
