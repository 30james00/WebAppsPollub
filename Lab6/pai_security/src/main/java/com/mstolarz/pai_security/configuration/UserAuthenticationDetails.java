package com.mstolarz.pai_security.configuration;

import com.mstolarz.pai_security.dao.UserDao;
import com.mstolarz.pai_security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserAuthenticationDetails implements UserDetailsService {
    @Autowired
    private UserDao dao;

    @Override
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException {
        User user = dao.findByLogin(login);
        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            grantedAuthorities.add(new SimpleGrantedAuthority("normalUser"));
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(),
                    true, true, true, true, grantedAuthorities);
        } else {
            throw
                    new UsernameNotFoundException("Wrong login or password.");
        }
    }
}