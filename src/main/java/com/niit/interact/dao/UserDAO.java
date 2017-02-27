package com.niit.interact.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.interact.model.User;

@Repository
public interface UserDAO {

	public boolean saveOrUpdate(User user);

	public boolean delete(User user);

	public List<User> list();

	public List<User> getuser(int id);

	public User oneuser(int id);

	public User authuser(String username, String password);

	public User profileof(String username);
	
	public List<User> nonfriends(int id);
}
