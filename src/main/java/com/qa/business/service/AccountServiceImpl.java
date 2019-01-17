package com.qa.business.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.qa.persistance.domain.AccountTable;
import com.qa.persistance.repository.Repo;
import com.qa.util.JSONUtil;

import javassist.bytecode.analysis.Util;

public class AccountServiceImpl implements AccountService {
	
	// in this class is where we put the business rules that we will be using for our application
	// business rules go here!
	@Inject
	private Repo repo;
	
	@Inject
	private JSONUtil util;
	
	public String getAllAccounts() {
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		if(util.getObjectForJSON(account, AccountTable.class).getAccountNumber().equals("9999")) {
			return "Error! Account number cannot = 9999";
		}
		else {
		return repo.createAccounts(account);
		}
	}

	@Override
	public String deleteAccount(Long id) {
		return repo.deleteAccounts(id);
	}

	public void setRepo(Repo repo) {
		this.repo = repo;
	}
	
}