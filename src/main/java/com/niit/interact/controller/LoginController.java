package com.niit.interact.controller;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
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

	org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	UserDAO userDAO;
	@Autowired
	FriendDAO friendDAO;

	@GetMapping("/login/")
	public ResponseEntity<Users> login(@RequestHeader("username") String username,
			@RequestHeader("password") String password, HttpSession session) {
		System.err.println("Hello: " + username + " : " + password);
		Users users = userDAO.authuser(username, password);
		if (users == null) {
			logger.debug("Users Data: " + users);
			Users users1 = new Users();
			users1.errorCode = "404";
			logger.debug("Users Data set: " + users1.getErrorCode());

			return new ResponseEntity<Users>(users1, HttpStatus.OK);

		} else if (friendDAO.getfriendlist(username) == null) {
			session.setAttribute("userLogged", users);
			session.setAttribute("userid", users.getId());
			session.setAttribute("username", users.getUsername());
			users.setStatus('o');
			userDAO.saveOrUpdate(users);
			Users users1 = userDAO.oneuser(users.getId());
			users1.setErrorCode("200");
			return new ResponseEntity<Users>(users1, HttpStatus.OK);
		} else {
			session.setAttribute("userLogged", users);
			session.setAttribute("userid", users.getId());
			session.setAttribute("username", users.getUsername());
			session.setAttribute("UserLoggedIn", "true");
			users.setStatus('o');
			userDAO.saveOrUpdate(users);
			List<Friend> friend = friendDAO.setonline(users.getUsername());
			for (int i = 0; i < friend.size(); i++) {
				Friend online = friend.get(i);
				online.setIsonline('y');
				friendDAO.saveOrUpdate(online);
			}
			Users users1 = userDAO.oneuser(users.getId());
			users1.setErrorCode("200");
			return new ResponseEntity<Users>(users1, HttpStatus.OK);
		}
	}

	@PostMapping("/logout")
	public ResponseEntity<Users> logout(HttpSession session) {
		int userid = (Integer) session.getAttribute("userid");
		Users users = userDAO.oneuser(userid);
		users.setStatus('N');
		userDAO.saveOrUpdate(users);
		List<Friend> friend = friendDAO.setonline(users.getUsername());
		for (int i = 0; i < friend.size(); i++) {
			Friend online = friend.get(i);
			online.setIsonline('f');
			friendDAO.saveOrUpdate(online);
		}
		session.invalidate();
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
}