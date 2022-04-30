package com.example.course.service;

import com.example.course.auth.UserPrincipal;
import com.example.course.datamodels.User;
import com.example.course.repositories.UserRepository;
import com.example.course.utils.UserRoles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Qualifier("MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Encoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Credentials");
        }
        return new UserPrincipal(user);
    }
    
    public UserDetails registerUser(User user) throws Exception {
        user.setRole(UserRoles.USER);
        String encodedPassword = this.bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            this.userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Failed to register!!!");
        }
        return new UserPrincipal(user);
    }
}
