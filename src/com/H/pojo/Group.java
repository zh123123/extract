package com.H.pojo;

import java.util.List;

public class Group {
	private long groupId;
	private String gname;
	private String detail;
	private List<Emgroup> emGroupList;

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<Emgroup> getEmGroupList() {
		return emGroupList;
	}

	public void setEmGroupList(List<Emgroup> emGroupList) {
		this.emGroupList = emGroupList;
	}

	@Override
	public String toString() {
		return "Group [groupId=" + groupId + ", gname=" + gname + ", detail=" + detail + "]";
	}

}
