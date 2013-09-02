package com.jpanotes.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@Table (name="user")
@NamedQueries({
	@NamedQuery(name="user.getAll", query = "from User"),
	@NamedQuery(name="user.findByName", query = "from User where name = :name")
})
public class User {
	private String emailAddress;
	private Boolean verified;
	private java.util.Date lastAccessTime;
	private java.util.Calendar registrationDate;
	private Long Id;
	private String password;
	private String name;

	@Column (name="login_name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@GeneratedValue
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Basic
	public Boolean getVerified() {
		return verified;
	}
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public java.util.Date getLastAccessTime() {
		return lastAccessTime;
	}
	public void setLastAccessTime(java.util.Date lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	public java.util.Calendar getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(java.util.Calendar registrationDate) {
		this.registrationDate = registrationDate;
	}
	//Why does Hibernate require no argument constructor?
	//http://stackoverflow.com/questions/2935826/why-does-hibernate-require-no-argument-constructor/2971717#2971717
	public User(){

	}

	public String toString(){
		return "id :" + Id + "\n name " + name + " \n registration Date:" + registrationDate;
	}

}
