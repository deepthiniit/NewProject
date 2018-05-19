package com.niit.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.Model.BlogComment;
import com.niit.Model.BlogPost;

@Repository("blogpostDao")
public interface BlogPostDao {
	void addBlogPost(BlogPost blogPost);

	List<BlogPost> listofBlogs(int approved);

	BlogPost getBlog(int id);

	void approve(BlogPost blog);

	void reject(BlogPost blog, String rejectionReason);

	void addBlogComment(BlogComment blogComment);

	List<BlogComment> getAllBlogComment(int blogPostId);

}
