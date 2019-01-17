package com.qa.persistance.repository;

public interface Repo {
	
	//crud!
	String createAccounts(String account);
	String getAllAccounts();
	String deleteAccounts(Long id);
	String updateAccounts(Long id, String input, String toChange);
}
