package com.niit.DaoImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.BlogPostLikesDao;
import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;
import com.niit.Model.User;

@Repository("blogpostlikesDao")
@EnableTransactionManagement
@Transactional
public class BlogPostLikesDaoImpl implements BlogPostLikesDao {
	@Autowired
	private SessionFactory sessionFactory;

	public BlogPostLikesDaoImpl() {

		System.out.println("BlogPostDaoImpl bean is created");

	}

	public BlogPostLikes hasUserLikedBlog(int blogId, String email) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from BlogPostLikes where blogPost.id=" + blogId + " and user.email='" + email + "'");
		// query.setInteger(0,blogId);
		// query.setString(1,email);
		BlogPostLikes blogPostLikes = (BlogPostLikes) query.uniqueResult();
		// System.out.println(blogPostLikes.getUser());
		return blogPostLikes;

	}

	public BlogPost updateLikes(int id, String email) {
		Session session = sessionFactory.getCurrentSession();
		BlogPostLikes blogPostLikes = hasUserLikedBlog(id, email);
		BlogPost blogPost = (BlogPost) session.get(BlogPost.class, id);
		if (blogPostLikes == null) {
			blogPostLikes = new BlogPostLikes();
			User user = (User) session.get(User.class, email);

			blogPostLikes.setBlogPost(blogPost);
			blogPostLikes.setUser(user);
			session.save(blogPostLikes);
			blogPost.setLikes(blogPost.getLikes() + 1);
			session.update(blogPost);
		} else {
			session.delete(blogPostLikes);
			blogPost.setLikes(blogPost.getLikes() - 1);
			session.update(blogPost);
		}
		return blogPost;
	}

}
