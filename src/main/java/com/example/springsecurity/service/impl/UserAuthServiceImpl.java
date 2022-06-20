package com.example.springsecurity.service.impl;

import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.UserAuthRepository;
import com.example.springsecurity.service.UserAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserAuthServiceImpl implements UserAuthService {
    private final UserAuthRepository userAuthRepository;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    private User user;

    private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
        String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        System.out.println(authorities);
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAuthRepository.findByEmail(username);
        if (user == null) throw new UsernameNotFoundException(username);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getActive(), accountNonExpired, credentialsNonExpired, accountNonLocked, getAuthorities(user));
    }


}
