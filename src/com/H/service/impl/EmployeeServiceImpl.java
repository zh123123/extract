package com.H.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.H.dao.EmployeeDao;
import com.H.pojo.Employee;
import com.H.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public List<Employee> selectEmployeeByIds(long[] ids) {
		return employeeDao.selectEmployeeByIds(ids);
	}
	
	@Override
	public void deleteByEid(long eid) {
		employeeDao.deleteByEid(eid);
		
	}

	@Override
	public int countEmployee() {
		return employeeDao.countEmployee();
	}

	@Override
	public void deleteEmployeeByEgIds(long[] egIds) {
		employeeDao.deleteEmployeeByEgIds(egIds);
	}

	@Override
	public void insertEmployee(String ename, String phone, String info, long egId) {
		Employee employee = new Employee();
		employee.setEname(ename);
		employee.setPhone(phone);
		employee.setEgId(egId);
		employee.setInfo(info);
		employeeDao.insertEmployee(employee);
	}
}
