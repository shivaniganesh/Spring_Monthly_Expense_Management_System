package com.mph.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mph.entity.UserProfile;

@Repository
public class UserProfileDaoImpl implements UserProfileDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void addUserProfile(UserProfile userProfile) {
		getSession().saveOrUpdate(userProfile);
		System.out.println("User stored Successfully in DB !!!");
		
	}

	@Override
	public List<UserProfile> updateUserProfile(UserProfile userProfile) {
		//check the query!!!!!!!!!!!!!!!
		Query query = getSession().createQuery("update UserProfile userprofile set fname=:fname,lname=:lname,email=:email,password=:password,phoneNumber=:phoneNumber where userId=:usid");
		query.setParameter("fname", userProfile.getFname());
		query.setParameter("lname", userProfile.getLname());
		query.setParameter("email", userProfile.getEmail());
		query.setParameter("password", userProfile.getPassword());
		query.setParameter("phoneNumber", userProfile.getPhoneNumber());
		query.setParameter("usid", userProfile.getUserId());
		int noofrows = query.executeUpdate();
		if(noofrows >0)
		{
			System.out.println("Updated " + noofrows + " rows");
		}
		
		return getAllUserProfile();
	}

	@Override
	public List<UserProfile> deleteUserProfile(int userId) {
		//check the query!!!!!!!!!!!!!!!
		System.out.println(userId+ "dao");
		Query query2 = getSession().createQuery("delete from Income income where userId=:uid");
		/* query2.setParameter("uid", userId); */
		Query query1 = getSession().createQuery("delete from Expense expense where userId=:uid");
		Query query = getSession().createQuery("delete from UserProfile userprofile where userId=:uid");
		query2.setParameter("uid", userId);
		query1.setParameter("uid", userId);
		query.setParameter("uid", userId);
		int noofrows2 = query2.executeUpdate();
		if(noofrows2 >0)
		{
			System.out.println("Deleted " + noofrows2 + " rows");
		}
		int noofrows1 = query1.executeUpdate();
		if(noofrows1 >0)
		{
			System.out.println("Deleted " + noofrows1 + " rows");
		}
		
		int noofrows = query.executeUpdate();
		if(noofrows >0)
		{
			System.out.println("Deleted " + noofrows + " rows");
		}
		
		return getAllUserProfile();
	}

	@Override
	public List<UserProfile> getAllUserProfile() {
		//checkQuery!!!!!!!!!!!!!!!
		Query query = getSession().createQuery("from UserProfile userprofile");
		List<UserProfile> userList = query.list();
		System.out.println(userList);
		return userList;
	}
	
	@Override
	public UserProfile login(String email, String password) {
		// TODO Auto-generated method stub
		Query query = getSession().createQuery("from UserProfile where email=:email and password=:password");
		query.setParameter("email", email);
		query.setParameter("password", password);
		UserProfile up = (UserProfile) query.uniqueResult();
		System.out.println("From login.." + up);
		return up;
	}
	
	@Override
	public UserProfile findByEmail(String email) {
		Query query = getSession().createQuery("from UserProfile where email=:email");
		query.setParameter("email", email);
		UserProfile em = (UserProfile) query.uniqueResult();
		System.out.println("from find by Email method.." + em);
		return em;
	}
	
	@Override
	public UserProfile resetPassword(String email, String password) {

		Query query = getSession().createQuery("update UserProfile set password =:password where email=:email");
		query.setParameter("email", email);
		query.setParameter("password", password);
		int rs = query.executeUpdate();
		if (rs > 0) {
			System.out.println("Updated " + rs + " rows");
		}

		return null;

	}

}
