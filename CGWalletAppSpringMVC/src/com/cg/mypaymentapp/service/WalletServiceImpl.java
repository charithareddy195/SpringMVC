package com.cg.mypaymentapp.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.beans.Wallet;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.repo.TransactionRepo;
import com.cg.mypaymentapp.repo.WalletRepo;

@Component(value="walletService")
public class WalletServiceImpl implements WalletService{
	@Autowired
	private WalletRepo repo;
	@Autowired
	private TransactionRepo trans;
	Customer customer;
	Wallet wallet;
	Transactions transaction;
	public WalletServiceImpl() {
	}
	public Customer createAccount(Customer customer){
			repo.save(customer);
			transaction=new Transactions(customer.getMobileNo(), "ACCOUNT CREATED", customer.getWallet().getBalance(), "Success", new Date().toString());
			trans.save(transaction);			
		return repo.save(customer);		
	}

	public Customer showBalance(String mobileNo) {
		customer=repo.findOne(mobileNo);
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no");	
	}
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {

		Customer customer=null;
		Customer customer1=null;
		if(sourceMobileNo.equals(targetMobileNo)){
			throw new InvalidInputException("Source and target mobile numbers are equal");
		}
		
			customer=repo.findOne(sourceMobileNo);
			customer1=repo.findOne(targetMobileNo);
		
		BigDecimal balance1;
		BigDecimal balance2;

		balance1=customer.getWallet().getBalance();
		balance2=customer1.getWallet().getBalance();
			if(( balance1).compareTo(amount)>=0) {
				balance1=balance1.subtract(amount);
				customer.setWallet(new Wallet(balance1));
				repo.save(customer);
				transaction=new Transactions(customer.getMobileNo(), "FUND TRANSFER", customer.getWallet().getBalance(), "Success", new Date().toString());
				trans.save(transaction);

				balance2=balance2.add(amount);	
				customer1.setWallet(new Wallet(balance2));
				repo.save(customer1);
				transaction=new Transactions(customer.getMobileNo(), "FUND TRANSFER", customer.getWallet().getBalance(), "Success", new Date().toString());
				trans.save(transaction);
				return customer ;
			}
			else
				throw new  InsufficientBalanceException("Insufficient balance");
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		Customer customer=null;

			customer=repo.findOne(mobileNo);
			BigDecimal dbalance;
			dbalance=customer.getWallet().getBalance();
				dbalance=dbalance.add(amount);
			customer.setWallet(new Wallet(dbalance));
			repo.save(customer);
			transaction=new Transactions(customer.getMobileNo(), "DEPOSITED", customer.getWallet().getBalance(), "Success", new Date().toString());
			trans.save(transaction);
			return customer ;

	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		Customer customer=null;
			customer=repo.findOne(mobileNo);
			BigDecimal wbalance;
			wbalance=customer.getWallet().getBalance();
			if(( wbalance).compareTo(amount)>=0) {
				wbalance=wbalance.subtract(amount);
			customer.setWallet(new Wallet(wbalance));
			repo.save(customer);
			transaction=new Transactions(customer.getMobileNo(), "WITHDRAWN", customer.getWallet().getBalance(), "Success", new Date().toString());
			trans.save(transaction);
			return customer;
			}
			else
				throw new  InsufficientBalanceException("Insufficient balance");
	}

	public List<Transactions> Transactions(String mobileNo) {
		List<Transactions> transaction = trans.findByphoneNumber(mobileNo);
		if(trans!=null) {
			return transaction;
		}
		else throw new InvalidInputException("No transactions to display"); 	
	}
	@Override
	public List<Customer> getDetails(BigDecimal balance) {
		List<Customer> cust=repo.find(balance);
		return cust;
		
	}
	@Override
	public List<Customer> getAllDetails() {
		List<Customer> customers;
		customers=repo.findAll();
		return customers;
	}
	
	
}