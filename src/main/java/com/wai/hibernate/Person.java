package com.wai.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

@Entity
public class Person {
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	
	@Embedded
	private Address homeAddress;
	
	@Transient
	private Address officeAddress;
	
	@Id
	@Column(name = "person_id")
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int id;
	
	 @ElementCollection
	   //( fetch = FetchType.EAGER ) 
	   @JoinTable( name = "PERSON_INSURANCES", joinColumns = @JoinColumn( name = "PERSON_ID" ) )
	   private Collection<Insurance> insurances = new ArrayList<Insurance>();
	
	public Person() {
		
	}
	
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address address) {
		this.homeAddress = address;
	}
	public Collection<Insurance> getInsurances() {
	      return insurances;
	}

	public void setInsurances( Collection<Insurance> insurance ){
	      this.insurances = insurance;
	}
	
	 @Override
	public String toString(){
	      return "Person id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +  ", officeAddress=" + officeAddress + ", homeAddress=" + homeAddress;
	}

	
	

}
