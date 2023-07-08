package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import dao.IUser;
import model.User;

@Configuration
public class UserService {
	
	@Autowired
	IUser user;
	
	User newUser;
	User ut=null;
	
	public String deposit(long amount, User newUser) {
		newUser.setBalance(newUser.getBalance()+amount);
		user.save(newUser);
		return "Amount Deposited Succesfully";
	}
	
	public String withdraw(long amount, User newUser) {
		if(newUser.getBalance()<amount) {
			return "Insufficient Balance";
		}else {
			newUser.setBalance(newUser.getBalance()-amount);
			user.save(newUser);
			return "Amount Withdraw Successfully";
		}
		
	}
	
	public String transferMoney(long anumber, long amount, User newUser) {
		if(newUser.getBalance()<amount) {
			return "Insufficient Balance";
		}else {
			newUser.setBalance(newUser.getBalance()-amount);
			user.save(newUser);
			ut = user.findByAnumber(anumber);
			ut.setBalance(ut.getBalance()+amount);
			user.save(ut);
			return "You have completed your transaction Succefully";
		}
		
	}

}
