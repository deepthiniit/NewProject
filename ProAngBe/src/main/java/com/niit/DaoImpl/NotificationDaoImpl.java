package com.niit.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.NotificationDao;
import com.niit.Model.Notification;

@Repository("notificationDao")
@EnableTransactionManagement
@Transactional
public class NotificationDaoImpl implements NotificationDao {
	@Autowired
	private SessionFactory sessionFactory;

	public NotificationDaoImpl() {

		System.out.println("NotificationDaoImpl bean is created");
	}

	public List<Notification> getNotificationsNotViewed(String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notification where email=? and viewed=0");
		query.setString(0, email);
		List<Notification> notificationsNotViewed = query.list();
		return notificationsNotViewed;
	}

	public List<Notification> getNotifications(String email, int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Notification where email=? and id=?");
		query.setString(0, email);
		query.setInteger(1, id);
		List<Notification> notification = query.list();
		return notification;
	}

	public Notification updateNotification(int id) {
		Session session = sessionFactory.getCurrentSession();
		Notification notification = (Notification) session.get(Notification.class, id);
		notification.setViewed(true);
		session.update(id);
		session.getTransaction().commit();
		return notification;
	}

}
