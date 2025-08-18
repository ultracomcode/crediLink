package com.main.CrediLink.domain.user;

import com.main.CrediLink.domain.user.dto.RequestUserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserEntity save(RequestUserDTO requestUserDTO){

        var userEntity = new UserEntity();

        BeanUtils.copyProperties(requestUserDTO, userEntity);

        return userRepository.save(userEntity);
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }
}
