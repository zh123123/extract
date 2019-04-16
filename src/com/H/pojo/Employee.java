package com.H.pojo;

public class Employee {
	private long eid;
	private String ename;
	private String phone;
	private String info;
	private long egId;

	public long getEid() {
		return eid;
	}

	public void setEid(long eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public long getEgId() {
		return egId;
	}

	public void setEgId(long egId) {
		this.egId = egId;
	}

	@Override
	public String toString() {
		return "Employee [eid=" + eid + ", ename=" + ename + ", phone=" + phone + ", info=" + info + ", egId=" + egId
				+ "]";
	}

}
