package com.niit.interact.dao;

import java.util.List;

import com.niit.interact.model.Friend;

public interface FriendDAO {
	
	public boolean saveOrUpdate(Friend friend);
	
	public boolean delete(Friend friend);
	
	public Friend newrequest(String userid,int friendid);
	
	/*getfriendlist is list of friends that are accepted by the user*/
	public List<Friend> getfriendlist(String userid);
	
	public List<Friend> getrequestlist(String userid);
	
	public List<Friend> setonline(String userid);

}
