package com.main.CrediLink.domain.user;

import com.main.CrediLink.domain.user.dto.RequestUserDTO;
import com.main.CrediLink.domain.user.dto.ResponseSaveUserDTO;
import com.main.CrediLink.domain.user.dto.ResponseUserDTO;
import com.main.CrediLink.sippulse.dto.AccountCodesDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<Page<ResponseUserDTO>> findAll(@PageableDefault(
            page = 0,
            size = 10,
            direction = Sort.Direction.ASC
    ) Pageable pageable){

        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @GetMapping("/users/accountcodes")
    public ResponseEntity<AccountCodesDTO> findAllAccountcode(){
        return ResponseEntity.ok(userService.findAllAccountcode());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<ResponseUserDTO> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/users/save")
    public ResponseEntity<ResponseSaveUserDTO> save(@RequestBody @Valid RequestUserDTO requestUserDTO){
        return ResponseEntity.ok(userService.save(requestUserDTO));
    }
}
