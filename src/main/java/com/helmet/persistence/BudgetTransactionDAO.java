package com.helmet.persistence;

import java.util.List;

import com.helmet.bean.BudgetTransaction;
import com.helmet.bean.BudgetTransactionUser;

public interface BudgetTransactionDAO {

	public List<BudgetTransactionUser> getBudgetTransactionUsersForClient(Integer clientId, boolean showOnlyActive);
	public List<BudgetTransaction> getBudgetTransactionsForLocationJobProfile(Integer locationJobProfileId);
	public BudgetTransaction getBudgetTransaction(Integer budgetTransactionId);
	public int insertBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId);
//	public int updateBudgetTransaction(BudgetTransaction budgetTransaction, Integer auditorId);
	public int deleteBudgetTransaction(Integer budgetTransactionId, Integer noOfChanges, Integer auditorId);
}
