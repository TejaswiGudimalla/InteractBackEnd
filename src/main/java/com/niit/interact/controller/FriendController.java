package com.niit.interact.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.interact.dao.FriendDAO;
import com.niit.interact.dao.UserDAO;
import com.niit.interact.model.Friend;

@RestController
public class FriendController {

	
	@Autowired
	private FriendDAO friendDAO;
	@Autowired
	private UserDAO userDAO;

	@PostMapping(value="/sendrequest/{friendid}")
	public ResponseEntity<Friend> newfriend(Friend friend,@PathVariable("friendid") int friendid,HttpSession session){
		String userid=(String) session.getAttribute("username");
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setStatus('n');
		friend.setIsonline('o');
		friendDAO.saveOrUpdate(friend);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}

	@GetMapping(value="/myfriends")
	public ResponseEntity<List<Friend>> myfriends(HttpSession session){
		String userid =(String) session.getAttribute("username");
		//List<Users> u1 = null;
		List<Friend> list=friendDAO.getfriendlist(userid);
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
	}
	@GetMapping(value="/newrequests")
	public ResponseEntity<List<Friend>> newrequests(HttpSession session){
		int userid=(Integer) session.getAttribute("username");
		List<Friend> list=friendDAO.getrequestlist(userid);
		return new ResponseEntity<List<Friend>>(list,HttpStatus.OK);
	}
	@PostMapping(value="/acceptrequest/{friendid}")
	public ResponseEntity<Friend> acceptfriend(@PathVariable("friendid") int friendid,HttpSession session){
		String userid=(String) session.getAttribute("username");
		Friend friend=friendDAO.newrequest(userid, friendid);
		friend.setStatus('a');
		friendDAO.saveOrUpdate(friend);
		Friend friend1=new Friend();
		friend1.setUserid(userid);
		friend1.setFriendid(friendid);
		friend1.setStatus('a');
		friendDAO.saveOrUpdate(friend1);
		return new ResponseEntity<Friend>(friend,HttpStatus.OK);
	}
	@PostMapping(value="/rejectrequest/{friendid}")
	public ResponseEntity<Friend> rejectfriend(@PathVariable("friendid") int friendid,HttpSession session){
		String userid=(String) session.getAttribute("username");
		Friend friend=friendDAO.newrequest(userid, friendid);
		friend.setStatus('r');
		friendDAO.saveOrUpdate(friend);
		return new ResponseEntity<Friend>(HttpStatus.OK);
	}
	@PostMapping("/unfriend/{friendid}")
	public ResponseEntity<Friend> unfriend(@PathVariable("friendid") String friendid,HttpSession session){
		return null;
		
	}
	}
