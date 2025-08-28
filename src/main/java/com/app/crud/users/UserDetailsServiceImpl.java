package com.app.crud.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

/**
 * Loads user from DB and maps to Spring Security UserDetails.
 * It expects role stored in DB with 'ROLE_' prefix (e.g. ROLE_USER).
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.app.crud.users.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                // use authorities() because role stored includes ROLE_ prefix
                .authorities(user.getRole())
                .build();
    }
}
