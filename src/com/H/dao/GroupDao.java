package com.H.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.H.pojo.Emgroup;
import com.H.pojo.Group;

public interface GroupDao {
	/***
	 * 	查出所有大分组
	 * @return
	 */
	List<Group> selectAllGroup();
	
	/**
	 * 	根据所属的groupId查出小分组
	 * @param groupId
	 * @return
	 */
	List<Emgroup> selectEmGroupByGroupId(long groupIds);
	
	
	/**
	 * 	通过id查找分组
	 * @param id
	 * @return
	 */
	Emgroup selectByegId(long egId);
	
	/**
	 * 	添加大分组
	 * @param group
	 * @return
	 */
	int insertGroup(String gname);
	
	int insertEmGroup(@Param("egname") String egname, @Param("groupId") long groupId);
	
	int updateGroup(@Param("gname") String gname,@Param("groupId") long groupId);
	
	int updateEmgroup(@Param("egname")String egname, @Param("egId")long egId);
	
	int deleteEmGroup(long groupId);
	
	int deleteGroup(long groupId);
	
	Group selectGroupById(long groupId);
	
	void deleteEmGroupByegId(long egId);
	
}
