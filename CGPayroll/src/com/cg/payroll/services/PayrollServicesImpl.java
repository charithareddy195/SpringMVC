package com.cg.payroll.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.daoservices.PayrollDAOServices;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;

@Component(value="payrollServices")
public class PayrollServicesImpl implements PayrollServices{
@Autowired
private PayrollDAOServices payrollDAO;
Associate associate;
	@Override
	public Associate acceptAssociateDetails(Associate associate) {
		return payrollDAO.save(associate);
	}

	@Override
	public int calculateNetSalary(int associateId) throws AssociateDetailsNotFoundException {
	
		return 0;
	}

	@Override
	public Associate getAssociateDetails(int associateId) throws AssociateDetailsNotFoundException {
		associate=payrollDAO.findOne(associateId);
		if(associate!=null)
			return associate;
		else
			throw new AssociateDetailsNotFoundException("Not Found");
	}

	@Override
	public ArrayList<Associate> getAllAssociatesDetails() {
		
		return new ArrayList<Associate>(payrollDAO.findAll());
	}

}
