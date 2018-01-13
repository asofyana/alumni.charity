package com.alumni.dao;

import java.util.List;

import com.alumni.entity.User;
import com.alumni.entity.UserRole;

public interface UserDao {
	public User getUserByEmail(String email);
	public void updateUser(User user);
	public void addUser(User user);
	public void addUserRole(UserRole userRole);
	public List<UserRole> getRoleListByUserId(int userId);
}
