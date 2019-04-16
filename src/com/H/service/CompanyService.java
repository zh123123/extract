package com.H.service;

import com.H.pojo.Company;

public interface CompanyService {

	
	Company selectCompany();
	
	boolean updateCompanyName(long id , String name);
	
}
