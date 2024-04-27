package edu.iu.lvanjelg.c322spring2024homework2.security;

import edu.iu.lvanjelg.c322spring2024homework2.model.Customer;
import edu.iu.lvanjelg.c322spring2024homework2.repository.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserDetailSecurityService implements UserDetailsService{
    CustomerRepository customerRepository;
    public UserDetailSecurityService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByUsername(username);
        if(customer == null){
            throw new UsernameNotFoundException("");
        }
        return User.withUsername(username).password(customer.getPassword()).build();
    }

}
