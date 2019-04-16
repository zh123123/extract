package com.H.dao;

import org.apache.ibatis.annotations.Param;

import com.H.pojo.Company;

public interface CompanyDao {

	Company selectCompany();

	int updateCompanyName(@Param("companyId")long id ,@Param("companyName") String companyName);
}
