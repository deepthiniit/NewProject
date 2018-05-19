package com.niit.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;
import com.niit.Model.Chat;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.Notification;
import com.niit.Model.ProfilePicture;
/*import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.Notification;*/
import com.niit.Model.User;

@Configuration
public class DBConfiguration {
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("secondone");
		dataSource.setPassword("pass");
		System.out.println(dataSource);
		return dataSource;
	}

	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");

		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(getDataSource());
		sessionFactoryBuilder.addProperties(hibernateProperties);
		sessionFactoryBuilder.addAnnotatedClass(User.class);
		sessionFactoryBuilder.addAnnotatedClass(Job.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogPost.class);
		sessionFactoryBuilder.addAnnotatedClass(Notification.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogPostLikes.class);
		sessionFactoryBuilder.addAnnotatedClass(BlogComment.class);
		sessionFactoryBuilder.addAnnotatedClass(ProfilePicture.class);
		sessionFactoryBuilder.addAnnotatedClass(Friend.class);
		sessionFactoryBuilder.addAnnotatedClass(Chat.class);
		SessionFactory sessionFactory = sessionFactoryBuilder.buildSessionFactory();
		System.out.println(sessionFactory);
		return sessionFactory;
	}

	@Bean(name = "hibernatetransactionmanager")
	public HibernateTransactionManager getTransaction(SessionFactory sessionFactory) {
		HibernateTransactionManager tm = new HibernateTransactionManager(sessionFactory);
		return tm;
	}
}