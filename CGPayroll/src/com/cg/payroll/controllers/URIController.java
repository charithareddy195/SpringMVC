package com.cg.payroll.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.cg.payroll.beans.Associate;

@Controller
public class URIController {
	@RequestMapping(value="/")
	public String getIndexPage() {
		return "indexPage";
	}
	@RequestMapping(value="/RegisterPage")
	public String getRegisterPage() {
		return "Register";
	}
	@ModelAttribute("associate")
	public Associate getCustomer() {
		return new Associate();
	}
	@RequestMapping(value="/AssociateDetails")
	public String getAssociateDetails() {
		return "login";
	}
	

}
