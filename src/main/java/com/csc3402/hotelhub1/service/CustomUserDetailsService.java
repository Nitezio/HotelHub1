package com.csc3402.hotelhub1.service;

import com.csc3402.hotelhub1.model.Customer;
import com.csc3402.hotelhub1.model.Staff;
import com.csc3402.hotelhub1.repository.CustomerRepository;
import com.csc3402.hotelhub1.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final StaffRepository staffRepo;
    private final CustomerRepository custRepo;

    @Autowired
    public CustomUserDetailsService(StaffRepository staffRepo,
                                    CustomerRepository custRepo) {
        this.staffRepo = staffRepo;
        this.custRepo  = custRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1) STAFF table?
        Staff staff = staffRepo.findByEmail(email).orElse(null);
        if (staff != null) {
            return User.withUsername(staff.getEmail())
                    .password(staff.getPassword())
                    .authorities(new SimpleGrantedAuthority("ROLE_STAFF"))
                    .build();
        }

        // 2) CUSTOMER table
        Customer c = custRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user with email: " + email));

        // 3) Use exactly whatever is in the `role` column (e.g. "ROLE_STAFF" or "ROLE_CUSTOMER")
        String grantedRole = c.getRole().startsWith("ROLE_")
                ? c.getRole()
                : "ROLE_" + c.getRole();

        return User.withUsername(c.getEmail())
                .password(c.getPassword())
                .authorities(new SimpleGrantedAuthority(grantedRole))
                .build();
    }
}
