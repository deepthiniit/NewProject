package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.NotificationDao;
import com.niit.Model.ErrorClass;
import com.niit.Model.Notification;

@RestController
public class NotificationController {
	@Autowired
	private NotificationDao notificationDao;

	public NotificationController() {
		System.out.println("NotificationController beanis created");
	}

	@RequestMapping(value = "/getnotifications", method = RequestMethod.GET)
	public ResponseEntity<?> getNotificationsNotViewed(HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access....");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);

		}
		List<Notification> notificationsNotViewed = notificationDao.getNotificationsNotViewed(email);
		return new ResponseEntity<List<Notification>>(notificationsNotViewed, HttpStatus.OK);
	}

	@RequestMapping(value = "/getnotification/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getNotifications(@PathVariable("id") int id, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access....");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);

		}
		List<Notification> notification = notificationDao.getNotifications(email, id);
		return new ResponseEntity<List<Notification>>(notification, HttpStatus.OK);
	}

	@RequestMapping(value = "/updateNotification/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNotification(HttpSession session, @PathVariable int id) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
		} else {
			Notification notification = notificationDao.updateNotification(id);

			return new ResponseEntity<Notification>(notification, HttpStatus.OK);
		}
	}
}
