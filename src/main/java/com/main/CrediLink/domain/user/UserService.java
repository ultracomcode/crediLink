package com.main.CrediLink.domain.user;

import com.main.CrediLink.domain.user.dto.RequestUserDTO;
import com.main.CrediLink.domain.user.dto.ResponseSaveUserDTO;
import com.main.CrediLink.domain.user.dto.ResponseUserDTO;
import com.main.CrediLink.domain.utils.AuthUtils;
import com.main.CrediLink.exceptions.UserException;
import com.main.CrediLink.sippulse.dto.AccountCodesDTO;
import com.main.CrediLink.sippulse.entity.Subscriber;
import com.main.CrediLink.sippulse.services.SubscriberService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscriberService subscriberService;
    private final AuthUtils authUtils;

    public UserService(UserRepository userRepository, SubscriberService subscriberService, AuthUtils authUtils) {
        this.userRepository = userRepository;
        this.subscriberService = subscriberService;
        this.authUtils = authUtils;
    }

    @Transactional
    public ResponseSaveUserDTO save(RequestUserDTO userDTO){

        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        String tipo = userDTO.role().toLowerCase();
        if (!tipo.equals("admin") && !tipo.equals("user")) {
            throw new IllegalArgumentException("Tipo de usuário inválido: " + userDTO.role());
        }

        userEntity.setPassword(cryptPassword(userDTO.password()));
        userEntity.setRole(tipo);

        userRepository.save(userEntity);

        return new ResponseSaveUserDTO(
                "Usuário cadastrado com sucesso!",
                LocalDateTime.now().toString()
        );
    }

    public Page<ResponseUserDTO> findAll(Pageable pageable){

        return userRepository.findAll(pageable)
                .map(ResponseUserDTO::fromEntity);
    }

    public ResponseUserDTO findById(Long id){
        return userRepository.findById(id)
                .map(ResponseUserDTO::fromEntity)
                .orElseThrow(() -> new UserException("Usuario Não encontrado"));
    }

    public UserDetails findbyUsername(String username){
        Optional<UserDetails> user = Optional
                .ofNullable(userRepository.findByUsername(username));

        return user.orElseThrow(() -> new UserException("Usuario Não encontrado"));
    }

    public AccountCodesDTO findAllAccountcode(){
        return subscriberService.findAllAccountcode(authUtils.getCurrentUser().getIdCrm());
    }

    private String cryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
