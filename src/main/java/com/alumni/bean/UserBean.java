package com.alumni.bean;

import java.util.List;

import com.alumni.entity.User;
import com.alumni.entity.UserRole;

public class UserBean {

	User user;
	private List<UserRole> roleList;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public List<UserRole> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<UserRole> roleList) {
		this.roleList = roleList;
	}

}
