package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.UserDao;
import com.niit.Model.User;

@Repository("userDao")
@EnableTransactionManagement
@Transactional
public class UserDaoImpl implements UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public UserDaoImpl() {

		System.out.println("UserDaoImpl bean is created");
		/* this.sessionFactory=sessionFactory; */
	}

	public void registerUser(User user) {
		System.out.println("register User in Dao" + user);
		Session session = sessionFactory.getCurrentSession();
		session.save(user);

	}

	public boolean isEmailUnique(String email) { // email - input from new user
		Session session = sessionFactory.getCurrentSession();
		// Generate the query
		// select * fromUser_s180250 wher email=?
		User user = (User) session.get(User.class, email);
		// 1object or null value
		// user value is null if the email entered bynew user is unique -
		// correct. -unique
		// user is not null, email entered by the new user is already exists -
		// incorrect
		if (user == null)
			return true;
		else
			return false;
	}

	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		// parameter positions are 0(string) 1(string)
		Query query = session.createQuery("from User where email=? and password=?");
		query.setString(0, user.getEmail());
		query.setString(1, user.getPassword());
		return (User) query.uniqueResult(); // object or null
		// 1 object for valid credentials
		// null for invalid credential

	}

	public void update(User validUser) {
		Session session = sessionFactory.getCurrentSession();
		session.update(validUser); // update User_180250 set online status=1
									// wher email=?

	}

	public User getUser(String email) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, email);
		return user;
	}

	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);

		return;
	}

}
