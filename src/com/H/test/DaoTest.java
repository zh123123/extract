package com.H.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.H.dao.AccountDao;
import com.H.dao.CompanyDao;
import com.H.dao.EmployeeDao;
import com.H.dao.GroupDao;
import com.H.pojo.Account;
import com.H.pojo.Company;
import com.H.pojo.Emgroup;
import com.H.pojo.Employee;
import com.H.pojo.Group;
import com.H.utils.MD5Util;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring 配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class DaoTest {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@Test
	public void test() {
		String username = "admin";
		String password = MD5Util.getMD5Code("123123");
		Account account = accountDao.selectForLogin(username, password);
		System.out.println(account); 
	}
	
	@Test
	public void test2() {
		Company company = companyDao.selectCompany();
		System.out.println(company);
		long id = company.getCompanyId();
		String name = "XXX公司";
		int i = companyDao.updateCompanyName(id, name);
		System.out.println(i);
		System.out.println(companyDao.selectCompany());
	}
	
	@Test
	public void group() {
		List<Group> list = groupDao.selectAllGroup();
		for (Group group : list) {
			System.out.println(group);
		}
		for (Group group : list) {
			List<Emgroup> list2 = groupDao.selectEmGroupByGroupId(group.getGroupId());
			for (Emgroup group2 : list2) {
				System.out.println(group2);
			}
		}
		
	}
	@Test
	public void test3() {
		long[] ids = new long[] {1,2};
		List<Employee> list = employeeDao.selectEmployeeByIds(ids);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}
	
	@Test
	public void insertGroup() {
		
		String egname="新员工分组";
		groupDao.insertEmGroup(egname, 5);
	}
}
