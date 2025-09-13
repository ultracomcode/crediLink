package com.main.CrediLink.domain.pix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.main.CrediLink.domain.pix.dto.ResponsePixDto;
import com.main.CrediLink.domain.pix.dto.RequestPixDTO;
import com.main.CrediLink.domain.token.ItauTokenService;
import com.main.CrediLink.domain.utils.CurrentUserService;
import com.main.CrediLink.domain.pix.exceptions.PixException;
import com.main.CrediLink.itau.dto.PixRequest;
import com.main.CrediLink.itau.dto.responsePix.createPixPaymentDTO;
import com.main.CrediLink.itau.feing.ItauPixClient;
import feign.FeignException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class PixService {
    @Value("${itau.chave.pix}")
    private String chavePix;

    @Value("${itau.client-id}")
    private String clientId;

    private final ItauPixClient itauPixClient;
    private final ItauTokenService itauTokenService;
    private final PixRepository pixRepository;
    private final CurrentUserService currentUserService;

    public PixService(ItauPixClient itauPixClient, ItauTokenService itauTokenService, PixRepository pixRepository, CurrentUserService currentUserService) {
        this.itauPixClient = itauPixClient;
        this.itauTokenService = itauTokenService;
        this.pixRepository = pixRepository;
        this.currentUserService = currentUserService;
    }

    public ResponsePixDto createPixPayment(RequestPixDTO userDTO) {
        try {


            var validatedValue = validateAndFormatAmount(userDTO.value());

            var request = buildPixRequest(validatedValue);

            createPixPaymentDTO dto = itauPixClient.gerarPix(
                    getAuthHeader(),
                    getCorrelationIdHeader(),
                    getFlowIdHeader(),
                    request
            );

            if (dto == null) {
                throw new PixException("Resposta vazia do Itaú");
            }

            return save(dto, userDTO);

        } catch (FeignException e) {
            if (e.status() != 201) {
                throw new PixException(
                        "Falha na criação do Pix. Status: " + e.status() + " - Corpo: " + e.contentUTF8(),
                        e
                );
            }
            throw e;
        }catch (PixException e) {
            throw e;
        } catch (Exception e) {
            throw new PixException("Erro ao criar cobrança Pix", e);
        }
    }

    private ResponsePixDto save(createPixPaymentDTO dto, RequestPixDTO userDTO) {

        PixEntity entity = buildPixEntity(dto, userDTO);

        var qrCodeBase64 = createQRCodeImage(dto.pixCopiaECola());
        entity.setQrcode(qrCodeBase64);

        pixRepository.save(entity);

        return ResponsePixDto.fromEntity(entity);

    }

    private PixRequest buildPixRequest(String valor) {
        PixRequest request = new PixRequest();
        var value = new PixRequest.Valor();
        value.setOriginal(valor);
        request.setValor(value);
        request.setChave(chavePix);
        return request;
    }

    private PixEntity buildPixEntity(createPixPaymentDTO dto, RequestPixDTO userDTO) {
        PixEntity entity = new PixEntity();
        BeanUtils.copyProperties(dto, entity);

        entity.setCriacao(OffsetDateTime.parse(dto.calendario().criacao()));
        entity.setValor(dto.valor().original());
        entity.setExpiracao(dto.calendario().expiracao());
        entity.calcularDataExpiracao();
        entity.setAccountcode(userDTO.accountCode());
        entity.setObservacao(userDTO.obs());
        entity.setUser(currentUserService.getCurrentUser());

        return entity;
    }

    private String validateAndFormatAmount(String valor) {
        double parsed;
        try {
            parsed = Double.parseDouble(valor.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new PixException("Valor inválido: " + valor);
        }

        if (parsed <= 0) {
            throw new PixException("Valor deve ser maior que zero");
        }

        return String.format(Locale.US, "%.2f", parsed);
    }

    private String createQRCodeImage(String payload) {
        try {
            Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, StandardCharsets.UTF_8.name());
            hints.put(EncodeHintType.MARGIN, 1);

            BitMatrix bitMatrix = new MultiFormatWriter()
                    .encode(payload, BarcodeFormat.QR_CODE, 300, 300, hints);

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
                return Base64.getEncoder().encodeToString(out.toByteArray());
            }
        } catch (Exception e) {
            throw new PixException("Erro ao gerar QR Code", e);
        }
    }

    private String getAuthHeader() {
        return "Bearer " + itauTokenService.getToken();
    }

    private String getFlowIdHeader() {
        return "1";
    }

    private String getCorrelationIdHeader() {
        return UUID.randomUUID().toString();
    }

    public Page<ResponsePixDto> listAll(Pageable pageable) {

        if (currentUserService.isAdmin()){
            return pixRepository.findAll(pageable)
                    .map(ResponsePixDto::fromEntity);
        }

        return pixRepository.findByUserId(currentUserService.getCurrentUser().getId(), pageable)
                .map(ResponsePixDto::fromEntity);
    }
}
