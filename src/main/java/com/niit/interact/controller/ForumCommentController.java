package com.niit.interact.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.interact.dao.ForumCommentDAO;
import com.niit.interact.model.ForumComment;

@RestController
public class ForumCommentController {

	@Autowired
	private ForumCommentDAO forumCommentDAO;
	
	@PostMapping(value="/commentforum/{friendid}")
	public ResponseEntity<ForumComment> forumcomment(@RequestBody ForumComment forumcomment,HttpSession session,@PathVariable("friendid") int friendid){
		int userid=(Integer) session.getAttribute("userid");
		forumcomment.setForumid(friendid);
		forumcomment.setUserid(userid);
		forumcomment.setCommenttime(new Date());
		forumCommentDAO.saveOrUpdate(forumcomment);
		return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/getforumcomment/{friendid}")
	public ResponseEntity<List<ForumComment>> getcomment(@PathVariable("friendid") int friendid){
		List<ForumComment> comments =forumCommentDAO.list(friendid);
		return new ResponseEntity<List<ForumComment>>(comments,HttpStatus.OK);
	}
}