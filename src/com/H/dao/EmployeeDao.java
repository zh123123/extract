package com.H.dao;

import java.util.List;

import com.H.pojo.Employee;

public interface EmployeeDao {

	/**
	 *	通过员工分组id查询出分组下的员工
	 * @return
	 */
	List<Employee> selectEmployeeByIds(long[] ids);
	
	/**
	 * 	根据eid删除数据
	 * @param eid
	 */
	void deleteByEid(long eid);
	
	int countEmployee();
	
	int deleteEmployeeByEgIds(long[] egIds);
	
	void insertEmployee(Employee employee);
}
