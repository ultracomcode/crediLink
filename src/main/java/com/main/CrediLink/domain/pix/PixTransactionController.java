package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.pix.dto.RequestPixDTO;
import com.main.CrediLink.domain.pix.dto.ResponsePixDto;
import com.main.CrediLink.dtos.ResponseDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class PixTransactionController {

    private final PixTransactionService pixTransactionService;

    public PixTransactionController(PixTransactionService pixTransactionService) {
        this.pixTransactionService = pixTransactionService;
    }

    @GetMapping("/pix")
    public ResponseEntity<Page<ResponsePixDto>> listAll(@PageableDefault(
            page = 0,
            size = 10,
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pixTransactionService.listAll(pageable));
    }

    @PostMapping("/pix/create")
    public ResponseEntity<ResponseDTO> createPixPayment(@RequestBody @Valid RequestPixDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pixTransactionService.generatePixRequest(dto));
    }

    @DeleteMapping("/pix/delete/{txid}")
    public ResponseEntity<ResponseDTO> cancelPix(@PathVariable String txid) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pixTransactionService.cancelPix(txid));

    }

}
