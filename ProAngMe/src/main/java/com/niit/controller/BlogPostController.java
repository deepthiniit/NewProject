package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.BlogPostDao;
import com.niit.Dao.UserDao;
import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;
import com.niit.Model.ErrorClass;
import com.niit.Model.User;

@RestController
public class BlogPostController {
	@Autowired
	private BlogPostDao blogPostDao;
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/addBlog", method = RequestMethod.POST)
	public ResponseEntity<?> addBlogPost(@RequestBody BlogPost blogPost, HttpSession session) {
		System.out.println(blogPost.getBlogContent());
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		blogPost.setPostedOn(new Date());
		User postedBy = userDao.getUser(email);
		blogPost.setPostedBy(postedBy);
		try {
			blogPostDao.addBlogPost(blogPost);
			return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);

		} catch (Exception e) {
			ErrorClass error = new ErrorClass(6, "Unable to post blog.." + e.getMessage());
			return new ResponseEntity<ErrorClass>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getblogs/{approved}", method = RequestMethod.GET)

	public ResponseEntity<?> getAllBlogs(@PathVariable int approved, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		if (approved == 0) {
			User user = userDao.getUser(email);
			if (!user.getRole().equals("ADMIN")) {
				ErrorClass error = new ErrorClass(7, "Access denied");
				return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
			}
		}
		List<BlogPost> blogs = blogPostDao.listofBlogs(approved);// 0 or 1
		return new ResponseEntity<List<BlogPost>>(blogs, HttpStatus.OK);
	}

	@RequestMapping(value = "/getblog/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBlog(@PathVariable int id, HttpSession session) {

		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);

		}
		BlogPost blogPost = blogPostDao.getBlog(id);
		return new ResponseEntity<BlogPost>(blogPost, HttpStatus.OK);

	}

	@RequestMapping(value = "/approve", method = RequestMethod.PUT)
	public ResponseEntity<?> approve(@RequestBody BlogPost blog, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		User user = userDao.getUser(email);
		if (!user.getRole().equals("ADMIN")) {
			ErrorClass error = new ErrorClass(7, "Access denied");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}

		blogPostDao.approve(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/reject/{rejectionReason}", method = RequestMethod.PUT)
	public ResponseEntity<?> reject(@RequestBody BlogPost blog, @PathVariable String rejectionReason,
			HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		User user = userDao.getUser(email);
		if (!user.getRole().equals("ADMIN")) {
			ErrorClass error = new ErrorClass(7, "AccessDEnied");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		blogPostDao.reject(blog, rejectionReason);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/addcomment/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> addBlogComment(@PathVariable int id, @RequestBody BlogComment blogComment,
			HttpSession session) {

		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}

		User commentedBy = userDao.getUser(email);
		blogComment.setCommentedon(new Date());
		blogComment.setCommentedBy(commentedBy);
		BlogPost bp = blogPostDao.getBlog(id);
		blogComment.setBlogPost(bp);
		try {
			blogPostDao.addBlogComment(blogComment);
		} catch (Exception e) {
			ErrorClass error = new ErrorClass(6, "Unable to post the comment" + e.getMessage());
			return new ResponseEntity<ErrorClass>(error, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		return new ResponseEntity<BlogComment>(blogComment, HttpStatus.OK);
	}

	@RequestMapping(value = "/blogcomments/{blogPostId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllComments(@PathVariable int blogPostId, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		if (email == null) {
			ErrorClass error = new ErrorClass(5, "Unauthorised access...");
			return new ResponseEntity<ErrorClass>(error, HttpStatus.UNAUTHORIZED);
		}
		List<BlogComment> blogComments = blogPostDao.getAllBlogComment(blogPostId);
		return new ResponseEntity<List<BlogComment>>(blogComments, HttpStatus.OK);
	}
}
