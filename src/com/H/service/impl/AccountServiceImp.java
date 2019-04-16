package com.H.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.H.dao.AccountDao;
import com.H.pojo.Account;
import com.H.service.AccountService;
import com.H.utils.MD5Util;

@Service
public class AccountServiceImp implements AccountService{

	@Autowired
	private AccountDao accountDao;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Account selectForLogin(String username, String password) {
		return accountDao.selectForLogin(username, MD5Util.getMD5Code(password));
	}

	@Override
	public void updatePassword(String username, String password) {
		accountDao.updatePassword(username, password);
	}
	
	
}
