package com.main.CrediLink.domain.pix;

import com.main.CrediLink.domain.pix.dto.RequestPixDTO;
import com.main.CrediLink.domain.pix.dto.ResponsePixDto;
import com.main.CrediLink.ixc.dto.IxcResponseDTO;
import com.main.CrediLink.ixc.service.InvoiceService;
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
public class PixController {

    private final PixService pixService;
    private final InvoiceService invoiceService;

    public PixController(PixService pixService, InvoiceService invoiceService) {
        this.pixService = pixService;
        this.invoiceService = invoiceService;
    }

    @PostMapping("/pix/create")
    public ResponseEntity<ResponsePixDto> createPixPayment(@RequestBody @Valid RequestPixDTO dto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pixService.createPixPayment(dto));
    }

    @GetMapping("/pix/solicitacoes")
    public ResponseEntity<Page<ResponsePixDto>> listAll(@PageableDefault(
            page = 0,
            size = 10,
            direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pixService.listAll(pageable));
    }
}
