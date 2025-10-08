package com.main.CrediLink.application.pix.controller;

import com.main.CrediLink.application.pix.service.PixTransactionService;
import com.main.CrediLink.application.pix.dto.RequestPixDTO;
import com.main.CrediLink.application.pix.dto.ResponsePixDto;
import com.main.CrediLink.application.pix.dto.ResponsePixSave;
import com.main.CrediLink.shared.dtos.ResponseDTO;
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
            size = 5,
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pixTransactionService.listAll(pageable));
    }

    @PostMapping("/pix/create")
    public ResponseEntity<ResponsePixSave> createPixPayment(@RequestBody @Valid RequestPixDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pixTransactionService.generatePixRequest(dto));
    }

    @PostMapping("/pix/expired/{txid}")
    public ResponseEntity<Void> changeStatusToExpired(@PathVariable String txid) {

        pixTransactionService.expired(txid);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/pix/cancel/{txid}")
    public ResponseEntity<ResponseDTO> cancelPix(@PathVariable String txid) {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pixTransactionService.cancelPix(txid));

    }

}
