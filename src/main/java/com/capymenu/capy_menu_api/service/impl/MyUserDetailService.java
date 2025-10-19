package com.capymenu.capy_menu_api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capymenu.capy_menu_api.models.entity.User;
import com.capymenu.capy_menu_api.repository.IUserRepository;

@Service
public class MyUserDetailService implements UserDetailsService  {

    IUserRepository userRepository;

    public MyUserDetailService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Email or password are wrong"));

        List<GrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        if(user.getUserData().getIsVerified()) {
            authorities.add(new SimpleGrantedAuthority("verified"));
        }

        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), 
            user.getPassword(), 
            true, 
            true, 
            true, 
            true, 
            authorities
        );
    }

}
