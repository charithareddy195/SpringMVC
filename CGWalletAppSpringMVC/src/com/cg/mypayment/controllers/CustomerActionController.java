package com.cg.mypayment.controllers;


import java.util.List;
import java.math.BigDecimal;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.mypaymentapp.beans.Customer;
import com.cg.mypaymentapp.beans.Transactions;
import com.cg.mypaymentapp.exception.InsufficientBalanceException;
import com.cg.mypaymentapp.exception.InvalidInputException;
import com.cg.mypaymentapp.service.WalletService;

@Controller
public class CustomerActionController {
	public static String mobileNo;
	@Autowired
	WalletService walletServices;
	@RequestMapping(value="/registerCustomer")
	public ModelAndView registerCustomer(@Valid@ModelAttribute("customer")Customer customer,BindingResult result) {
			if(result.hasErrors())
				return new ModelAndView("Register"); 
			customer=walletServices.createAccount(customer);
		return new ModelAndView("RegistrationSuccesspage","customer",customer);
	}
	@RequestMapping(value="/showMenu")
	public ModelAndView showMenu(@ModelAttribute("customer")Customer customer) { 
		
		try{	mobileNo=customer.getMobileNo();		
			customer=walletServices.showBalance(mobileNo);
		return new ModelAndView("Operations","customer",customer);
		}catch(InvalidInputException e) {
			return new ModelAndView("login","errorMessage",e.getMessage());
		}
		
	}
	@RequestMapping(value="/showBalancePage")
	public ModelAndView showBalance() { 
		Customer customer=walletServices.showBalance(mobileNo);
		return new ModelAndView("showBalancepage","customer",customer);
	}
	
	@RequestMapping(value="/depositAction")
	public ModelAndView depositBalance(@ModelAttribute("customer")Customer customer,@RequestParam("wallet.balance")BigDecimal amount) { 
		
			Customer customer1=walletServices.depositAmount(mobileNo,amount);
		return new ModelAndView("showBalancepage","customer",customer1);
		
	}
	@RequestMapping(value="/withdrawAction")
	public ModelAndView withdrawBalance(@ModelAttribute("customer")Customer customer,@RequestParam("wallet.balance")BigDecimal amount) { 
		try{
			Customer customer1=walletServices.withdrawAmount(mobileNo, amount);
		return new ModelAndView("showBalancepage","customer",customer1);
		}catch(InsufficientBalanceException e) {
			return new ModelAndView("withdrawpage","errorMessage",e.getMessage());
		}
	}
	@RequestMapping(value="/fundTransferAction")
	public ModelAndView fundTransfer(@ModelAttribute("customer")Customer customer,@RequestParam("mobileNo")String mobileNum,@RequestParam("wallet.balance")BigDecimal amount) { 
		try{
			Customer customer1=walletServices.fundTransfer(mobileNo, mobileNum, amount);
		return new ModelAndView("showBalancepage","customer",customer1);
	}catch(InsufficientBalanceException e) {
		return new ModelAndView("fundTransferPage","errorMessage",e.getMessage());
	}
		
		
	}
	@RequestMapping(value="/transactions1")
	public ModelAndView PrintTransactions(){ 
		List<Transactions> transaction=walletServices.Transactions(mobileNo);
		return new ModelAndView("printTransactions","transactions",transaction);
	}
	@RequestMapping(value="/getAll")
	public ModelAndView ShowAll(){ 
		List<Customer> cust=walletServices.getAllDetails();
		return new ModelAndView("showAll","customer",cust);
	}
	@RequestMapping(value="/getDeatils")
	public ModelAndView ShowDetails(@RequestParam("wallet.balance")BigDecimal balance){ 
		List<Customer> cust=walletServices.getDetails(balance);
		return new ModelAndView("showDetailsSuccessPage","customer",cust);
	}
}
