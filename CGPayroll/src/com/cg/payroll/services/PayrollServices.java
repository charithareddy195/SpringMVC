package com.cg.payroll.services;
import java.util.ArrayList;

import com.cg.payroll.beans.Associate;
import com.cg.payroll.exceptions.AssociateDetailsNotFoundException;

public interface PayrollServices {

	Associate acceptAssociateDetails(Associate associate);

	int calculateNetSalary(int associateId)throws AssociateDetailsNotFoundException;

	Associate getAssociateDetails(int associateId)throws AssociateDetailsNotFoundException ;

	ArrayList<Associate> getAllAssociatesDetails();

}