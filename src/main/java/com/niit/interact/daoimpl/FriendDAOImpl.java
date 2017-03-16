package com.niit.interact.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.interact.dao.FriendDAO;
import com.niit.interact.model.Friend;

@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
@Repository
public class FriendDAOImpl implements FriendDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public FriendDAOImpl(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Friend friend) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

   @Transactional
	public boolean delete(Friend friend) {
		try {
			sessionFactory.getCurrentSession().delete(friend);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
   
   @Transactional
   public Friend newrequest(String userid,int friendid) {
   	String hql="from Friend where userid='"+userid+"' and friendid='"+friendid+"'";
   	Query query =sessionFactory.getCurrentSession().createQuery(hql);
   	List<Friend> list=query.list();
   	if(list==null){
   		return null;
   	}else{
   		return list.get(0);
   	}
   }
   
   @Transactional
   public List<Friend> getfriendlist(String userid) {
   	String hql="from Friend where userid='"+userid+"' and status='a'";
   	Query query=sessionFactory.getCurrentSession().createQuery(hql);
   	List<Friend> list = query.list();
   	return list;
   }

   @Transactional
   public List<Friend> getrequestlist(String userid) {
   	String hql="from Friend where friendid='"+userid+"' and status='n'";
   	Query query=sessionFactory.getCurrentSession().createQuery(hql);
   	List<Friend> list = query.list();
   	return list;
   }

   @Transactional
   public List<Friend> setonline(String userid) {
   	String hql="from Friend where friendid='"+userid+"'";
   	Query query = sessionFactory.getCurrentSession().createQuery(hql);
   	List<Friend> list=query.list();
   	return list;
   }
   
}
