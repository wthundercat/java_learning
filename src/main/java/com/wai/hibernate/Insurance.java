package com.wai.hibernate;

import javax.persistence.Embeddable;

@Embeddable
public class Insurance {
	
	private String insuranceCompanyName;
	
	public Insurance() {
		
		
	}
	
	public Insurance(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}

	public String getInsuranceCompanyName() {
		return insuranceCompanyName;
	}

	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	
	
	@Override
	public String toString() {
		return "Insurance Company Name is: " + insuranceCompanyName;
	}

}
