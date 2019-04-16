package com.H.controller;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.H.pojo.Emgroup;
import com.H.pojo.Employee;
import com.H.pojo.Group;
import com.H.service.CompanyService;
import com.H.service.EmployeeService;
import com.H.service.GroupService;
import com.H.utils.JSONResult;

@Controller
@RequestMapping("/infocenter")
public class InfocenterController {
	
	@Autowired
	private CompanyService companyService;
	@Autowired 
	private EmployeeService employeeService;
	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/getCompanyName")
	@ResponseBody
	public JSONResult getCompanyName() {
		
		return JSONResult.ok(companyService.selectCompany());
		
	}
	
	@RequestMapping("/updateCompanyName")
	@ResponseBody
	public JSONResult updateCompanyName(long id ,String name) {
		boolean flag = companyService.updateCompanyName(id, name);
		return flag ?  JSONResult.ok("成功") : JSONResult.errorMsg("编辑失败！");
	}
	
	@RequestMapping("cancellation")
	public String cancellation(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@RequestMapping("/employee")
	public String employee( long[] ids,ModelMap map) {
		if(ids.length!=0) {
			List<Employee> employeeList = employeeService.selectEmployeeByIds(ids);
			Emgroup emgroup = groupService.selectByegId(ids[0]);
			map.addAttribute("employeeList", employeeList);
			map.addAttribute("emgroup",emgroup);
		}
		return "/employee";
	}
	
	@RequestMapping("/delete")
	public String delete(long[] ids ,long eid , ModelMap map) {
		employeeService.deleteByEid(eid);
		List<Employee> employeeList = employeeService.selectEmployeeByIds(ids);
		Emgroup emgroup = groupService.selectByegId(ids[0]);
		map.addAttribute("employeeList", employeeList);
		map.addAttribute("emgroup",emgroup);
		return "/employee";
	}
	
	@RequestMapping("/manage")
	public String manage(ModelMap map) {
		List<Group> groups = groupService.selectAllGroup();
		map.addAttribute("groups", groups);
		return "/manage";
	}
	
	@RequestMapping("/editGroup")
	@ResponseBody
	public JSONResult editGroup(String gname ,long groupId) {
		if(StringUtils.isEmpty(gname)) {
			return JSONResult.errorMsg("参数出错");
		}
		groupService.updateGroup(gname, groupId);
		return JSONResult.ok();
	}
	
	@RequestMapping("deleteGroup")
	public String deleteGroup(long groupId) {
		
		List<Emgroup> emgroups = groupService.selectEmGroupByGroupId(groupId);
		if(!(emgroups==null || emgroups.size()==0)) {
			long[] egIds = new long[emgroups.size()];
			for (int i = 0; i < emgroups.size(); i++) {
				egIds[i] = emgroups.get(i).getEgId();
			}
			//删除分组下的所有employee
			employeeService.deleteEmployeeByEgIds(egIds);
		}
		//删除所有小分组
		groupService.deleteEmGroup(groupId);
		//删除分组
		groupService.deleteGroup(groupId);
		return "redirect:/infocenter/manage";
	}

	@RequestMapping("/insertGroup")
	@ResponseBody
	public JSONResult insertGroup(String gname) {
		if(StringUtils.isEmpty(gname))
			return JSONResult.errorMsg("参数出错");
		groupService.insertGroup(gname);
		return JSONResult.ok();
	}
	
	@RequestMapping("/emgroup")
	public String emgroup(long groupId,ModelMap map,HttpSession session) {
		Group group = groupService.selectGroupById(groupId);
		List<Emgroup> emgroupList = groupService.selectEmGroupByGroupId(groupId);
		map.addAttribute("emgroupList", emgroupList);
		session.setAttribute("group", group);
		return "/emgroup";
	}
	
	@RequestMapping("/insertEmgroup")
	@ResponseBody
	public JSONResult insertEmgroup(String egname,long groupId) {
		if(StringUtils.isEmpty(egname) || StringUtils.isEmpty(groupId)) {
			return JSONResult.errorMsg("参数出错");
		}
		groupService.insertEmGroup(egname, groupId);
		return JSONResult.ok();
	}
	
	@RequestMapping("/deleteEmGroup")
	public String deleteEmGroup(Long egId,Long groupId,ModelMap map) {
		if(egId != null) {
			long[] egIds = new long[1];
			egIds[0] = egId;
			employeeService.deleteEmployeeByEgIds(egIds);
			groupService.deleteEmGroupByegId(egId);
		}
		List<Emgroup> emgroupList = groupService.selectEmGroupByGroupId(groupId);
		map.addAttribute("emgroupList", emgroupList);
		return "/emgroup";
	}
	@RequestMapping("/editEmgroup")
	@ResponseBody
	public JSONResult editEmgroup(String egname,long egId) {
		System.out.println("egname" + egname);
		System.out.println("egId" + egId);
		if(StringUtils.isEmpty(egname) || StringUtils.isEmpty(egId)) {
			return JSONResult.errorMsg("参数出错");
		}
		
		groupService.updateEmgroup(egname, egId);
		
		return JSONResult.ok();
	}
	@RequestMapping("/insertEmployee")
	@ResponseBody
	public JSONResult insertEmployee(String ename,String phone,String info,long egId) {
		if(StringUtils.isEmpty("ename")||StringUtils.isEmpty("phone")||StringUtils.isEmpty("info")||StringUtils.isEmpty("egId")) {
			return JSONResult.errorMsg("参数出错");
		}
		employeeService.insertEmployee(ename, phone, info, egId);
		return JSONResult.ok();
	}
	
	@RequestMapping("/selectGroup")
	public String selectGroup(Long[] selectGroup,Integer number,ModelMap map) {
		System.out.println(selectGroup);
		System.out.println(number);
		if(selectGroup==null || selectGroup.length==0 || number==null) {
			
		}else {
			long[] ids = new long[selectGroup.length];
			for(int i = 0 ; i < selectGroup.length ; i++) {
				ids[i] = selectGroup[i];
			}
			List<Employee> employees = employeeService.selectEmployeeByIds(ids);
			if(number < employees.size()) {
				Collections.shuffle(employees);
				List<Employee> employeeList = new Vector<Employee>();
				for(int i = 0 ; i < number ; i ++) {
					employeeList.add(employees.get(i));
				}
				map.addAttribute("employeeList", employeeList);
			}else {
				map.addAttribute("employeeList", employees);
			}	
		}
		return "/result";
	}
}
