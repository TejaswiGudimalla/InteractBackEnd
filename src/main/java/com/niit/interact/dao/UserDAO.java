package com.niit.interact.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.interact.model.Users;

@Repository
public interface UserDAO {

	public boolean saveOrUpdate(Users user);

	public boolean delete(Users user);

	public List<Users> list();

	public List<Users> getuser(int id);

	public Users oneuser(int id);

	public Users authuser(String username, String password);

	public Users profileof(String username);
	
	public List<Users> nonfriends(int id);
}
