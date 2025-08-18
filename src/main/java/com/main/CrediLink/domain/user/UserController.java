package com.main.CrediLink.domain.user;

import com.main.CrediLink.domain.user.dto.RequestUserDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/save")
    public ResponseEntity<UserEntity> save(@RequestBody @Valid RequestUserDTO requestUserDTO){
        return ResponseEntity.ok(userService.save(requestUserDTO));
    }

    @RequestMapping("/findAll")
    public ResponseEntity<Iterable<UserEntity>> findAll(){
        return ResponseEntity.ok(userService.findAll());
    }
}
