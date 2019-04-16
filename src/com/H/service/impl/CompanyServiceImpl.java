package com.H.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.H.dao.CompanyDao;
import com.H.pojo.Company;
import com.H.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public Company selectCompany() {
		
		return companyDao.selectCompany();
		
	}

	@Override
	public boolean updateCompanyName(long id, String name) {
		int count = companyDao.updateCompanyName(id, name);
		return count > 0 ? true : false;
	}

}
