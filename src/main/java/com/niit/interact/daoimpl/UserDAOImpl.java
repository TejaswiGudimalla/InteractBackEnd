package com.niit.interact.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.interact.dao.UserDAO;
import com.niit.interact.model.User;

@SuppressWarnings("deprecation")
@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public List<User> list() {
		String hql ="from UserInteract";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return query.list();
	}

	@Transactional
	public User get(int id) {
		String hql="from UserInteract where id="+"'"+id+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		if(list==null){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Transactional
	public User validate(int id, String password) {
		String hql = "from UserInteract where id= '" + id + "' and password '" + password + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<User> list = query.list();
		if (list == null) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Transactional
	public boolean saveOrUpdate(User user) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Transactional
	public boolean delete(User user) {
		try {
			sessionFactory.getCurrentSession().delete(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public User get(String username) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(User.class);
		c.add(Restrictions.eq("username", username));
		List<User> listUser = (List<User>) c.list();
		if (listUser != null && !listUser.isEmpty()) {
			return listUser.get(0);
		} else {
			return null;
		}
	}

}
