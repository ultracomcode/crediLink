package com.main.CrediLink.application.user;

import com.main.CrediLink.application.user.dto.RequestAdminDTO;
import com.main.CrediLink.application.user.dto.RequestUpdateUserDTO;
import com.main.CrediLink.application.user.dto.RequestUserDTO;
import com.main.CrediLink.application.user.dto.ResponseUserDTO;
import com.main.CrediLink.application.user.enuns.StatusUser;
import com.main.CrediLink.application.user.exceptions.UserException;
import com.main.CrediLink.application.utils.CurrentUserService;
import com.main.CrediLink.integration.sippulse.dto.AccountCreditsDTO;
import com.main.CrediLink.integration.sippulse.services.SubscriberService;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SubscriberService subscriberService;
    private final CurrentUserService currentUserService;

    public UserService(UserRepository userRepository, SubscriberService subscriberService, CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.subscriberService = subscriberService;
        this.currentUserService = currentUserService;
    }

    @Transactional
    public ResponseDTO save(RequestUserDTO userDTO){

        var userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        String tipo = userDTO.role().toLowerCase();
        if (!tipo.equals("user")) {
            throw new IllegalArgumentException("Tipo de usuário inválido: " + userDTO.role());
        }

        userEntity.setPassword(cryptPassword(userDTO.password()));
        userEntity.setRole(tipo);

        userRepository.save(userEntity);

        return new ResponseDTO(
                "success",
                "Usuário cadastrado com sucesso!"
        );
    }

    @Transactional
    public ResponseDTO saveAdmin(RequestAdminDTO adminDTO){

        var userEntity = new UserEntity();
        BeanUtils.copyProperties(adminDTO, userEntity);

        String tipo = adminDTO.role().toLowerCase();
        if (!tipo.equals("admin")) {
            throw new IllegalArgumentException("Tipo de usuário inválido: " + adminDTO.role());
        }

        userEntity.setPassword(cryptPassword(adminDTO.password()));
        userEntity.setRole(tipo);

        userRepository.save(userEntity);

        return new ResponseDTO(
                "success",
                "Administrador cadastrado com sucesso!"
        );
    }

    public Page<ResponseUserDTO> findAll(Pageable pageable){

        return userRepository.findAll(pageable)
                .map(ResponseUserDTO::fromEntity);
    }

    public ResponseUserDTO findByIdentifier(UUID id){
        return userRepository.findByIdentifier(id)
                .map(ResponseUserDTO::fromEntity)
                .orElseThrow(() -> new UserException("Usuario Não encontrado"));
    }

    public UserDetails findbyUsername(String username){
        Optional<UserDetails> user = Optional
                .ofNullable(userRepository.findByUsername(username));

        return user.orElseThrow(() -> new UserException("Usuario Não encontrado"));
    }

    public AccountCreditsDTO findAllAccountcode(){

        return subscriberService.findAllAccountcode(currentUserService.getCurrentUser().getIdContrato());
    }

    public ResponseUserDTO findById(Long id) {
        return userRepository.findById(id)
                .map(ResponseUserDTO::fromEntity)
                .orElseThrow(() -> new UserException("Usuario Não encontrado"));
    }

    private String cryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Transactional
    public ResponseDTO toggleUserStatus(UUID id) {
        return userRepository.findByIdentifier(id)
                .map(user -> {
                    StatusUser newStatus = user.getStatus() == StatusUser.A ? StatusUser.I : StatusUser.A;
                    user.setStatus(newStatus);
                    userRepository.save(user);

                    String message = newStatus == StatusUser.A
                            ? "Usuário ativado com sucesso"
                            : "Usuário desativado com sucesso";

                    return new ResponseDTO("success", message);
                })
                .orElse(new ResponseDTO("error", "Usuário não encontrado"));
    }

    @Transactional
    public ResponseDTO updateUser(UUID id, RequestUpdateUserDTO dto) {
        UserEntity user = userRepository.findByIdentifier(id)
                .orElseThrow(() -> new UserException("Usuário não encontrado"));

        if (user.getStatus() == StatusUser.I){
            return new ResponseDTO("erro", "Usuário Inativo, Não pode ser alterado");
        }

        if (dto.password() != null && !dto.password().isEmpty()) {
            user.setPassword(cryptPassword(dto.password()));
        }

        if (dto.name() != null) user.setName(dto.name());
        if (dto.email() != null) user.setEmail(dto.email());
        if (dto.cpfCnpj() != null) user.setCpfCnpj(dto.cpfCnpj());
        if (dto.phone() != null) user.setPhone(dto.phone());
        if (dto.idCrm() != null) user.setIdCrm(dto.idCrm());
        if (dto.idContrato() != null) user.setIdContrato(dto.idContrato());

        userRepository.save(user);

        return new ResponseDTO("succes", "Usuário atualizado com sucesso");
    }

}
