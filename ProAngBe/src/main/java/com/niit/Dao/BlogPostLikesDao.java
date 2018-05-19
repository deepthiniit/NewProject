package com.niit.Dao;

import org.springframework.stereotype.Repository;

import com.niit.Model.BlogPost;
import com.niit.Model.BlogPostLikes;

@Repository("blogpostlikesDao")
public interface BlogPostLikesDao {
	BlogPostLikes hasUserLikedBlog(int blogId, String email);

	BlogPost updateLikes(int id, String email);
}
