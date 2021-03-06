package com.mph.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int expenseId;

	private String category;

	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-DD")
	private Date createdDate;

	private long expenseAmount;

	private String expenseDescription;

	@ManyToOne /* (cascade=CascadeType.REMOVE) */
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private UserProfile userProfile;

	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Expense(int expenseId, String category, Date createdDate, long expenseAmount, String expenseDescription,
			UserProfile userProfile) {
		super();
		this.expenseId = expenseId;
		this.category = category;
		this.createdDate = createdDate;
		this.expenseAmount = expenseAmount;
		this.expenseDescription = expenseDescription;
		this.userProfile = userProfile;
	}

	public int getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public long getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(long expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", category=" + category + ", createdDate=" + createdDate
				+ ", expenseAmount=" + expenseAmount + ", expenseDescription=" + expenseDescription + ", userProfile="
				+ userProfile + "]";
	}

	
}