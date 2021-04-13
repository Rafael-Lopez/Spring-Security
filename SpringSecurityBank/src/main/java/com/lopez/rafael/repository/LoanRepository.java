package com.lopez.rafael.repository;

import java.util.List;

import com.lopez.rafael.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends CrudRepository<Loans, Long> {
	//No user has ROOT role, so the method should not be invoked
	//@PreAuthorize("hasRole('ROOT')")
	@PreAuthorize("hasRole('USER')")
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
