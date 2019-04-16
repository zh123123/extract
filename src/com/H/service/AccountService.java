package com.H.service;

import com.H.pojo.Account;

public interface AccountService {
	
	Account selectForLogin(String username,String password);
	
	void updatePassword(String username,String password);
}
