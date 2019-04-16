package com.H.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.H.dao.GroupDao;
import com.H.pojo.Account;
import com.H.pojo.Emgroup;
import com.H.pojo.Group;
import com.H.service.AccountService;
import com.H.service.EmployeeService;
import com.H.service.GroupService;
import com.H.utils.JSONResult;
import com.H.utils.MD5Util;

@Controller
public class LoginController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private GroupService groupService;
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("")
	public String index() {
		
		return "/index";
	}
	
	@RequestMapping("/index")
	public String index2() {
		return "/index";
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public JSONResult login(String username , String password) {
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return JSONResult.errorMsg("请输入用户名和密码。");
		}
		Account account = accountService.selectForLogin(username, password);
		if(account == null)
			return JSONResult.errorMsg("用户名或密码错误！");
		return JSONResult.ok(account);
	}
	
	@RequestMapping("/updatePass")
	public String updatePass() {
		return "/updatepass";
	}
	@RequestMapping("/updatePassword")
	@ResponseBody
	public JSONResult updatePassword(String oldPass,String newPass,String newPass2) {
		if(StringUtils.isEmpty(oldPass) || StringUtils.isEmpty(newPass) ||StringUtils.isEmpty(newPass2)) {
			return JSONResult.errorMsg("参数出错。");
		}
		System.out.println("newPass" + newPass);
		System.out.println("newPass2" + newPass2);
		Account account = accountService.selectForLogin("admin", oldPass);
		if(account == null) {
			return JSONResult.errorMsg("原密码错误。");
		}
		if(!newPass.equals(newPass2)) {
			return JSONResult.errorMsg("两次输入的密码不一致。");
		}
		String code = MD5Util.getMD5Code(newPass);
		accountService.updatePassword("admin", code);
		return JSONResult.ok();
	}
	
	@RequestMapping("/infocenter")
	public String infocenter(String username,String password,HttpSession session) {
		Account account = (Account) session.getAttribute("account");
		if(account != null) {
			
		}else {
			if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				return "redirect:index";
			}
			account = accountService.selectForLogin(username, password);
			if(account == null)
				return "redirect:index";
			account.setPassword("");
			session.setAttribute("account", account);
		}
		//	查询相关数据
		// 查出所有大分组
		List<Group> groupList = groupService.selectAllGroup();
		// 查出大分组下的小分组
		for (Group group : groupList) {
			List<Emgroup> emGroupList = groupService.selectEmGroupByGroupId(group.getGroupId());
			group.setEmGroupList(emGroupList);
		}
		//库里人数
		int count = employeeService.countEmployee();
		session.setAttribute("groupList", groupList);
		session.setAttribute("count", count);
		return "/infocenter";
	}
	
}
