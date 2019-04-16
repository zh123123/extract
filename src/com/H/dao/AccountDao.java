package com.H.dao;

import org.apache.ibatis.annotations.Param;

import com.H.pojo.Account;

public interface AccountDao {
	
	/**
	 * 	通过用户名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	Account selectForLogin(@Param("username")String username , @Param("password")String password);
	
	void updatePassword(@Param("username") String username, @Param("password")String password);
}
