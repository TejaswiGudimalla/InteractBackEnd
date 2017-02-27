package com.niit.interact.dao;

import java.util.List;

import com.niit.interact.model.Friend;

public interface FriendDAO {
	
	public boolean saveOrUpdate(Friend friend);
	public boolean delete(Friend friend);
	public Friend newrequest(int userid,int friendid);
	public List<Friend> getfriendlist(int userid);
	public List<Friend> getrequestlist(int userid);
	public List<Friend> setonline(int userid);

}