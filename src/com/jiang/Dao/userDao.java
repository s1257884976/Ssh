package com.jiang.Dao;

import java.util.List;

import com.jiang.model.user;
//ÐÞ¸Ä
public interface userDao {
	public void insert(user u);
	public void update(user u);
	public void delete(user u);
	public List<user> select();

}
