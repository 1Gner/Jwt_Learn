package com.example.demo.config;
import com.example.demo.repository.userRepository;
import com.example.demo.model.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;

@Service
public class SecurtityDatabase implements UserDetailsService {
    @Autowired
    private userRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user userEntity = UserRepository.findyUsername(username);

        if(userEntity == null){
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> authoritities = new HashSet<GrantedAuthority>();
        userEntity.getRoles().forEach(role ->
                authoritities.add(new SimpleGrantedAuthority("ROLE_"+role)));

        UserDetails user1 = new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                authoritities);

        return user1;
    }
}
