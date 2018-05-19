package com.niit.Model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "newblogcomment_s180250")
@Component
public class BlogComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private BlogPost blogPost;
	@ManyToOne
	private User commentedBy;
	private Date commentedon;
	private String commentTxt;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BlogPost getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}

	public User getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(User commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Date getCommentedon() {
		return commentedon;
	}

	public void setCommentedon(Date commentedon) {
		this.commentedon = commentedon;
	}

	public String getCommentTxt() {
		return commentTxt;
	}

	public void setCommentTxt(String commentTxt) {
		this.commentTxt = commentTxt;
	}

}
