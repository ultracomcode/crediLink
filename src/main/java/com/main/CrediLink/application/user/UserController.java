package com.main.CrediLink.application.user;

import com.main.CrediLink.application.user.dto.RequestAdminDTO;
import com.main.CrediLink.application.user.dto.RequestUpdateUserDTO;
import com.main.CrediLink.application.user.dto.RequestUserDTO;
import com.main.CrediLink.application.user.dto.ResponseUserDTO;
import com.main.CrediLink.shared.dtos.ResponseDTO;
import com.main.CrediLink.integration.sippulse.dto.AccountCreditsDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<ResponseUserDTO>> findAll(@PageableDefault(
            direction = Sort.Direction.ASC
    ) Pageable pageable){

        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/users/accountcodes")
    public ResponseEntity<AccountCreditsDTO> findAllAccountcode(){
        return ResponseEntity.ok(userService.findAllAccountcode());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseUserDTO> findByIdentifier(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok(userService.findByIdentifier(id));
    }



    @PostMapping("/users/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid RequestUserDTO requestUserDTO){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(userService.save(requestUserDTO));
    }

    @PostMapping("/admin/save")
    public ResponseEntity<ResponseDTO> save(@RequestBody @Valid RequestAdminDTO requestAdminDTO){
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body(userService.saveAdmin(requestAdminDTO));
    }

    @PostMapping("/users/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable(value = "id") UUID id){
        return ResponseEntity.ok()
                .body(userService.toggleUserStatus(id));
    }

    @PutMapping("/users/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable(value = "id") UUID id,
                                              @RequestBody @Valid RequestUpdateUserDTO requestUpdateUserDTO){
        return ResponseEntity.ok()
                .body(userService.updateUser(id,requestUpdateUserDTO));
    }
}
