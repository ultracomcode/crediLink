package com.main.CrediLink.application.security;

import com.main.CrediLink.application.user.UserEntity;
import com.main.CrediLink.application.user.UserRepository;
import com.main.CrediLink.application.user.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public UserDTO toDTO(UserDetails user) {
        var userEntity = (UserEntity) user;
        return UserDTO.fromEntity(userEntity);
    }

}
