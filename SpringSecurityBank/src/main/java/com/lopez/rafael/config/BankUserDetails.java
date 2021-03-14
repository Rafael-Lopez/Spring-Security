package com.lopez.rafael.config;

import com.lopez.rafael.model.Customer;
import com.lopez.rafael.model.SecurityCustomer;
import com.lopez.rafael.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//We implement UserDetailsService because we only need to authenticate the user. If you
//need to also update, create, etc., then implement UserDetailsManager
@Service
public class BankUserDetails implements UserDetailsService {
    private CustomerRepository customerRepository;

    @Autowired
    public BankUserDetails(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Customer> customers = customerRepository.findByEmail(username);

        if(customers.isEmpty()) {
            throw new UsernameNotFoundException("User details not found for username: " + username);
        }
        return new SecurityCustomer(customers.get(0));
    }
}
