package com.niit.Dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.niit.Model.Job;

@Repository("jobDao")
public interface JobDao {
	void addJob(Job job);// insert into job_s180250 values(....)

	List<Job> getAllJobs();// select *from job_s180250

	Job getJob(int id);// select * from job_s180250 wher id=?

	void insertUser(Job job);
}
