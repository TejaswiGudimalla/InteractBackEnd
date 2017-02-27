package com.niit.interact.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.interact.dao.BlogDAO;
import com.niit.interact.model.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory sessionFactory;
	public BlogDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean saveOrUpdate(Blog blog) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Blog blog) {
		try {
			sessionFactory.getCurrentSession().delete(blog);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	@Transactional
	public Blog get(int id) {
		String hql = "from Blog where id='"+ id+"'" ;
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog>list= query.list();
		if(list==null)
		{
			return null;
		}
		else
		{
			return list.get(0);
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Transactional
	public List<Blog> list() {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Blog.class);
		List<Blog> list=c.list();
		return list;
	}

	@SuppressWarnings({ "deprecation", "unchecked", "rawtypes" })
	public List<Blog> userlist() {
		String hql= "from Blog where status='a'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Blog> list=query.list();
		if(list==null){
			return null;
		}
		else{
			return list;
		}
	}
	
	

}
