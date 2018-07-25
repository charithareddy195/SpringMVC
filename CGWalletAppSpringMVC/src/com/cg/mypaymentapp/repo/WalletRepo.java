package com.cg.mypaymentapp.repo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.mypaymentapp.beans.Customer;

public interface WalletRepo extends JpaRepository<Customer, String>{
	@Query("select cust from Customer cust join cust.wallet w where w.balance<:balance")
	List<Customer> find(@Param("balance")BigDecimal balance);
}
