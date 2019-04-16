package com.H.service;

import java.util.List;

import com.H.pojo.Employee;

public interface EmployeeService {
	
	List<Employee> selectEmployeeByIds(long[] ids);
	
	void deleteByEid(long eid);
	
	int countEmployee();
	
	void deleteEmployeeByEgIds(long[] egIds);

	void insertEmployee(String ename,String phone,String info ,long egId);
	
}
