package com.alumni.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "T_MEMBER_CONTRIBUTION", uniqueConstraints = { @UniqueConstraint(columnNames = { "ID" }) })
public class MemberContribution {

	private int id;
	Payment payment;
	User user;
	Date month;
	private double amount;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;

	@Id
	@Column(name="ID")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="PAYMENT_ID", referencedColumnName="ID")
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	@ManyToOne
	@JoinColumn(name="USER_ID", referencedColumnName="ID")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	@Column(name="MONTH")
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}

	@Column(name="AMOUNT")
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Column(name="CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name="CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name="UPDATED_DATE")
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Column(name="UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
