package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserAuthRepository;
import com.example.springsecurity.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAuthServiceImpl implements UserAuthService {
    private final UserAuthRepository userAuthRepository;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAuthRepository.findByEmail(username);
        log.info(user);
        if (user == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getActive(), accountNonExpired, credentialsNonExpired, accountNonLocked, new ArrayList<>());

    }
}
