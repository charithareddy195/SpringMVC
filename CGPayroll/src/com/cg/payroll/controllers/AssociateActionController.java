package com.cg.payroll.controllers;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;
import com.cg.payroll.services.PayrollServices;

@Controller
public class AssociateActionController {
@Autowired
PayrollServices payrollServices;
@Transactional
@RequestMapping(value="/registerAssociate")
public ModelAndView registerAssociate(@ModelAttribute("associate")Associate associate) {
		associate=payrollServices.acceptAssociateDetails(associate);
	return new ModelAndView("RegistrationSuccesspage","associate",associate);
}
@RequestMapping(value="/loginAssociate")
public ModelAndView login(@RequestParam("associateID")int associateID,@ModelAttribute("associate")Associate associate) throws AssociateDetailsNotFoundException {
	associate=payrollServices.getAssociateDetails(associateID);
	return new ModelAndView("AssociateDetailsPage","associate",associate);
}
@RequestMapping(value="/AssociatesList")
public ModelAndView PrintAssociatesList(){ 
	ArrayList<Associate> associates=payrollServices.getAllAssociatesDetails();
	return new ModelAndView("AssociatesListPage","associate",associates);
}
}
