package com.tedu.ctgu.dao;

import java.util.List;

import com.tedu.ctgu.pojo.User;

public interface UserDao {
	public List<User> findAll();
	public User findById();
}
