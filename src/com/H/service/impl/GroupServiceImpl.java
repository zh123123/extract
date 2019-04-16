package com.H.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.H.dao.GroupDao;
import com.H.pojo.Emgroup;
import com.H.pojo.Group;
import com.H.service.GroupService;
@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private GroupDao groupDao;
	@Override
	public List<Group> selectAllGroup() {
		
		return groupDao.selectAllGroup();
	}

	@Override
	public List<Emgroup> selectEmGroupByGroupId(long groupIds) {
		
		return groupDao.selectEmGroupByGroupId(groupIds);
	
	}

	@Override
	public Emgroup selectByegId(long egId) {
		
		return groupDao.selectByegId(egId);
	}

	@Override
	public void insertGroup(String gname) {
		
		groupDao.insertGroup(gname);
		
	}

	@Override
	public void updateGroup(String gname, long groupId) {
		groupDao.updateGroup(gname, groupId);
	}

	@Override
	public int deleteEmGroup(long groupId) {
		
		return groupDao.deleteEmGroup(groupId);
	}

	@Override
	public int deleteGroup(long groupId) {
		return groupDao.deleteGroup(groupId);
	}

	@Override
	public Group selectGroupById(long groupId) {
		
		return groupDao.selectGroupById(groupId);
	}

	@Override
	public void insertEmGroup(String egname, long groupId) {
		groupDao.insertEmGroup(egname, groupId);
	}

	@Override
	public void deleteEmGroupByegId(long egId) {
		groupDao.deleteEmGroupByegId(egId);
	}

	@Override
	public void updateEmgroup(String egname, long egId) {
		groupDao.updateEmgroup(egname, egId);
		
	}

	
	
}
