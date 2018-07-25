package com.cg.payroll.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.cg.payroll.beans.Associate;

public interface PayrollDAOServices extends JpaRepository<Associate, Integer>{

}