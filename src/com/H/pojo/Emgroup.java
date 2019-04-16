package com.H.pojo;

/**
 * 员工分组
 * 
 * @author H
 *
 */
public class Emgroup {
	private long egId;
	private String egname;
	private long groupId;
	private String detail;

	public long getEgId() {
		return egId;
	}

	public void setEgId(long egId) {
		this.egId = egId;
	}

	public String getEgname() {
		return egname;
	}

	public void setEgname(String egname) {
		this.egname = egname;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Emgroup [egId=" + egId + ", egname=" + egname + ", groupId=" + groupId + ", detail=" + detail + "]";
	}

}
