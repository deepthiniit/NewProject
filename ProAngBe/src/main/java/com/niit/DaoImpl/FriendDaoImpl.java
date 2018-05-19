package com.niit.DaoImpl;

import java.util.List;
import java.util.ListIterator;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.FriendDao;
import com.niit.Model.Friend;
import com.niit.Model.User;

@Repository("friendDao")
@EnableTransactionManagement
@Transactional
public class FriendDaoImpl implements FriendDao {
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> suggestedUsers(String email) {
		Session session = sessionFactory.getCurrentSession();
		String queryString = "select * from newuser_s180250 where email in"
				+ "(select email from newuser_s180250 where email!=?" + "minus"
				+ "(select toId_email from newfriend_s180250 where fromId_email=?"
				+ "union select fromId_email from newfriend_s180250 where toId_email=?))";
		SQLQuery query = session.createSQLQuery(queryString);
		query.setString(0, email);
		query.setString(1, email);
		query.setString(2, email);
		List<User> suggestedUsers = query.list();
		return suggestedUsers;
	}

	public void addFriend(Friend friend) {
		Session session = sessionFactory.getCurrentSession();
		session.save(friend);
	}

	public List<Friend> pendingRequests(String toIdEmail) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Friend where status=? and toId.email=?");
		query.setCharacter(0, 'P');
		query.setString(1, toIdEmail);
		List<Friend> pendingRequests = query.list();
		return pendingRequests;
	}

	public void acceptRequest(Friend request) {
		Session session = sessionFactory.getCurrentSession();
		request.setStatus('A');
		session.update(request);
	}

	public void deleteRequest(Friend request) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(request);
	}

	public List<Friend> listOfFriends(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query1 = session.createQuery("select f.toId from Friend f where f.fromId.email=? and f.status=?");
		query1.setString(0, email);
		query1.setCharacter(1, 'A');
		List<Friend> friendsList1 = query1.list();
		Query query2 = session.createQuery("select f.fromId from Friend f where f.toId.email=? and f.status=?");
		query2.setString(0, email);
		query2.setCharacter(1, 'A');
		List<Friend> friendsList2 = query2.list();
		friendsList1.addAll(friendsList2);
		return friendsList1;
	}

}
