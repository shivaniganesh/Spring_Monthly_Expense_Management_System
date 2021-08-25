package com.mph.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mph.entity.Expense;


@Repository
public class ExpenseDaoImpl implements ExpenseDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addExpense(Expense expense) {
		getSession().saveOrUpdate(expense);
		System.out.println("Expense entry stored Successfully in DB !!!");

	}

	@Override
	public List<Expense> updateExpense(Expense expense) {
		// check the query!!!!!!!!!!!!!!!
		Query query = getSession().createQuery(
				"update Expense expense set category=:cat,createdDate=:cdate,expenseAmount=:expenseAmount,expenseDescription=:expenseDescription where expenseId=:expid");
		query.setParameter("cat", expense.getCategory());
		query.setParameter("cdate", expense.getCreatedDate());
		query.setParameter("expenseAmount", expense.getExpenseAmount());
		query.setParameter("expenseDescription", expense.getExpenseDescription());
		query.setParameter("expid", expense.getExpenseId());
		int noofrows = query.executeUpdate();
		if (noofrows > 0) {
			System.out.println("Updated " + noofrows + " rows");
		}

		return getAllExpense();
	}

	@Override
	public List<Expense> deleteExpense(int expenseId) {
		// check the query!!!!!!!!!!!!!!!
		Query query = getSession().createQuery("delete from Expense expense where expenseId=:expid");
		query.setParameter("expid", expenseId);
		int noofrows = query.executeUpdate();
		if (noofrows > 0) {
			System.out.println("Deleted " + noofrows + " rows");
		}

		return getAllExpense();
	}

	@Override
	public List<Expense> getAllExpense() {
		// checkQuery!!!!!!!!!!!!!!!
		Query query = getSession().createQuery("from Expense expense");
		List<Expense> expenseList = query.list();
		System.out.println(expenseList);
		return expenseList;
	}

	@Override
	public List<Expense> getUserExpense(int userId) {
		Query query = getSession().createQuery("from Expense expense where userId=:uid");
		query.setParameter("uid", userId);
		

		List<Expense> expenseListSelected = query.list();
		System.out.println(expenseListSelected);
		return expenseListSelected;
	}

}
