package com.qa.persistance.repository;

import java.util.HashMap;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import com.qa.persistance.domain.AccountTable;
import com.qa.util.JSONUtil;

@Alternative
public class AccountMap implements Repo {

	
	private HashMap<Long, AccountTable> accounts = new HashMap<Long, AccountTable>();
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String createAccounts(String account) {
		AccountTable anAccount = util.getObjectForJSON(account, AccountTable.class);
		accounts.put(anAccount.getId(), anAccount);
		return "{Account Created}";
	}

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accounts);
	}

	@Override
	public String deleteAccounts(Long id) {
		accounts.remove(id);
		return "{Account deleted}";
	}

	@Override
	public String updateAccounts(Long id, String input, String toChange) {
		AccountTable anAccount = accounts.get(id);
		switch(toChange) {
		case "firstName":
			anAccount.setFirstName(input);
		case "lastName":
			anAccount.setSecondName(input);
		case "accountNumber":
			anAccount.setAccountNumber(input);
		default:
			break;
		}
		
		return "{Account Updated}";
	}
	
	
	

}
