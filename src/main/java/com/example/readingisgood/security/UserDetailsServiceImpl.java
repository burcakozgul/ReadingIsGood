package com.example.readingisgood.security;

import java.util.ArrayList;
import com.example.readingisgood.model.Customer;
import com.example.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByMail(mail);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found");
        }
        return new User(customer.getMail(), customer.getPassword(), new ArrayList<>());
    }
}
