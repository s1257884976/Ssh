package com.jiang.DaoImpl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.jiang.Dao.userDao;
import com.jiang.model.user;
import com.sun.beans.AppContext;
@Repository("userDaoImpl")
public class userDaoImpl implements userDao {

	@Resource
	private HibernateTemplate hibernateTemplate;
	
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	public void insert(user u) {
		hibernateTemplate.save(u);
	}

	
	public void update(user u) {
		hibernateTemplate.update(u);
	}

	
	public void delete(user u) {
		hibernateTemplate.delete(u);		
	}

	
	public List<user> select() {
		return hibernateTemplate.execute(new HibernateCallback<List<user>>() {

			public List<user> doInHibernate(Session session) throws HibernateException, SQLException {
				
				String sql="select * from user";
				SQLQuery sq=session.createSQLQuery(sql);
				List<user> l=sq.addEntity(user.class).list();
				return l;
			}
		});
	}

	//第一次更改了
	public static void main(String jiang[])
	{
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		userDaoImpl ui=(userDaoImpl)ac.getBean("userDaoImpl");
		user u=new user();
		List<user> l=ui.select();
		for(user user:l)
		{
			System.out.println("id  "+user.getId()+"   用户名"+user.getName()+"   角色"+user.getJuese());
		}

	}
	
}
