package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.Expense;

public interface ExpenseDAO {

	public List<Expense> getExpensesForLocation(Integer locationId, boolean showOnlyActive);
	public Expense getExpense(Integer expenseId);
	public Expense getExpenseForName(Integer locationId, String name);
	public Expense getExpenseForCode(Integer locationId, String code);
	public int insertExpense(Expense expense, Integer auditorId);
	public int updateExpense(Expense expense, Integer auditorId);
	public int deleteExpense(Integer expenseId, Integer noOfChanges, Integer auditorId);
	public int updateExpenseDisplayOrder(Integer expenseId, Integer displayOrder, Integer noOfChanges, Integer auditorId);

}
