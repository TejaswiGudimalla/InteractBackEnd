package com.niit.interact.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.interact.model.User;

@Repository
public interface UserDAO {

    public List<User> list();
	
	public User get(int id);
	
	public User validate(int id, String password);
	
	public boolean saveOrUpdate(User user);
	
	public boolean delete(User user);
	
	public User get(String username);
}
