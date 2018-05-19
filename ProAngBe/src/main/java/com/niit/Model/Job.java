package com.niit.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "newjob_s180250")
@Component
public class Job {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(nullable = false)
	private String jobTittle;
	private String jobDescription;
	@Column(nullable = false)
	private String skillsRequired;
	/* @NotEmpty(message="Location of company situated must be mentioned(*)") */
	private String location;
	/* @Min(value=2,message="Required work experience") */
	private String yrsofExp;
	/* @NotEmpty(message="Please mention the respected company(*)") */
	private String companyName;
	/* @Min(value=150000,message="minimum expected salary") */
	private String salary;
	private Date postedOn;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobTittle() {
		return jobTittle;
	}

	public void setJobTittle(String jobTittle) {
		this.jobTittle = jobTittle;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getSkillsRequired() {
		return skillsRequired;
	}

	public void setSkillsRequired(String skillsRequired) {
		this.skillsRequired = skillsRequired;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getYrsofExp() {
		return yrsofExp;
	}

	public void setYrsofExp(String yrsofExp) {
		this.yrsofExp = yrsofExp;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

}
