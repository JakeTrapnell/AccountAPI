package com.qa.persistance.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistance.domain.AccountTable;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBR implements Repo {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	@Override
	@Transactional(REQUIRED)
	public String createAccounts(String account) {
		AccountTable anAccount = util.getObjectForJSON(account, AccountTable.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Override
	@Transactional(REQUIRED)
	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM AccountTable a");
		Collection<AccountTable> accounts = (Collection<AccountTable>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Override
	@Transactional(REQUIRED)
	public String deleteAccounts(Long id) {
		AccountTable accountInDB = findAccount(id);
		if (accountInDB != null) {
			manager.remove(accountInDB);
		}
		return "{\"message\": \"account sucessfully deleted\"}";
	}
	
	private AccountTable findAccount(Long id) {
		return manager.find(AccountTable.class, id);
	}

	@Override
	@Transactional(REQUIRED)
	public String updateAccounts(Long id, String input, String toChange) {
		return "{functionality not yet completed}";	
	}
	
	

}
