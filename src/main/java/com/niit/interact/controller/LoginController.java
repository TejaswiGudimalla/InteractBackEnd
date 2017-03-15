package com.niit.interact.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.niit.interact.dao.FriendDAO;
import com.niit.interact.dao.UserDAO;
import com.niit.interact.model.Friend;
import com.niit.interact.model.Users;

@RestController
public class LoginController {
	@Autowired 
	UserDAO userDAO;
	@Autowired
	FriendDAO friendDAO;

	@GetMapping("/login/")
	public ResponseEntity<Users> login( @RequestHeader("username") String username, @RequestHeader("password") String password ,HttpSession session){
		Users users = userDAO.authuser(username,password);
		if(users==null)
			{	return null;
	}else if(friendDAO.getfriendlist(username)==null){
		session.setAttribute("userLogged", users);
		session.setAttribute("userid", users.getId());
		session.setAttribute("username",users.getUsername());
		users.setStatus('o');
		userDAO.saveOrUpdate(users);
		Users users1=userDAO.oneuser(users.getId());
		return new ResponseEntity<Users>(users1,HttpStatus.OK);
	}else{
		session.setAttribute("userLogged", users);
		session.setAttribute("userid", users.getId());
		session.setAttribute("username",users.getUsername());
		users.setStatus('o');
		userDAO.saveOrUpdate(users);
    	List<Friend> friend=friendDAO.setonline(users.getId());
    	for(int i=0;i<friend.size();i++){
    		Friend online=friend.get(i);
    		online.setIsonline('y');
    		friendDAO.saveOrUpdate(online);
    	}
		Users user1=userDAO.oneuser(users.getId());
		return new ResponseEntity<Users>(user1,HttpStatus.OK);
	}
	}
	
	@PostMapping("/logout")
	public ResponseEntity<Users> logout(HttpSession session){
		int uid =  (Integer) session.getAttribute("uid");
		Users user =userDAO.oneuser(uid);
		user.setStatus('N');
		userDAO.saveOrUpdate(user);
		List<Friend> friend=friendDAO.setonline(user.getId());
		for(int i=0;i<friend.size();i++){
    		Friend online=friend.get(i);
    		online.setIsonline('f');
    		friendDAO.saveOrUpdate(online);
    	}
		session.invalidate();
		return new ResponseEntity<Users>(user,HttpStatus.OK);
	}
}