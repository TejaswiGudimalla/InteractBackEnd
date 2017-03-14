package com.niit.interact.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.interact.dao.UserDAO;
import com.niit.interact.model.Users;

@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<Users> list() {
		String hql ="from UserInteract";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public List<Users> getuser(int id) {
		String hql="from UserInteract where id="+"'"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Users> list = query.list();
		if(list==null){
			return null;
		}else{
			return list;
		}
	}

	@Transactional
	public Users authuser(String username, String password) {
		String hql = "from UserInteract where id= '" + username + "' and password '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Users> list = query.list();
		/*if (list == null) {
			return null;
		} else {*/
			return list.get(0);
		/*}*/
	}

	@Transactional
	public boolean saveOrUpdate(Users user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean delete(Users user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public Users oneuser(int id) {
		String hql = "from UserInteract where id= " + "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Users> list = query.list();

		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}
	@Transactional
	public List<Users> nonfriends(int id) {
		String hql = "from UserInteract where id !='"+id+"'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Users> list= query.list();
		return list;
	}

	@Transactional
	public Users profileof(String username) {
		String hql = "from UserInteract where username='" + username + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Users> list = query.list();

		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

}
