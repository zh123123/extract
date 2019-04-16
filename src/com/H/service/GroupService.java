package com.H.service;

import java.util.List;

import com.H.pojo.Emgroup;
import com.H.pojo.Group;

public interface GroupService {
	
	List<Group> selectAllGroup();
	
	List<Emgroup> selectEmGroupByGroupId(long groupId);

	Emgroup selectByegId(long egId);
	
	void insertGroup(String gname);
	
	void insertEmGroup(String egname,long groupId);
	
	void updateGroup(String gname ,long groupId);
	
	void updateEmgroup(String egname,long egId);
	
	int deleteEmGroup(long groupId);
	
	int deleteGroup(long groupId);
	
	Group selectGroupById(long groupId);
	
	void deleteEmGroupByegId(long egId);
}
