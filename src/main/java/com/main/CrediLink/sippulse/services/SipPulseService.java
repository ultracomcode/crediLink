package com.main.CrediLink.sippulse.services;

import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.service.IntegrationService;
import com.main.CrediLink.exceptions.SoapIntegrationException;
import com.main.CrediLink.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.sippulse.wsdlSippulse.SipPulse;
import com.main.CrediLink.sippulse.wsdlSippulse.SubscriberWS;
import com.main.CrediLink.sippulse.wsdlSippulse.UserPrincipalDTO;
import com.main.CrediLink.utils.CryptoService;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

@Service
public class SipPulseService {

    private final IntegrationService integrationService;
    private final CryptoService cryptoService;

    public SipPulseService(IntegrationService integrationService, CryptoService cryptoService) {
        this.integrationService = integrationService;
        this.cryptoService = cryptoService;
    }


    private SubscriberWS getSubscriberWSPort() {
        try {
            URL wsdlUrl = new URL(integration().getUrlApi() + "?wsdl");
            SipPulse service = new SipPulse(wsdlUrl);
            return service.getSubscriberWSPort();
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL do serviço inválida: " + integration().getUrlApi(), e);
        }
    }

    public void addCredit(AddCreditDTO addCreditDTO) {
        try {
            SubscriberWS port = getSubscriberWSPort();
            UserPrincipalDTO principal = createUserPrincipal();

            port.addCredit(
                    addCreditDTO.username(),
                    addCreditDTO.domain(),
                    Double.parseDouble(addCreditDTO.value()),
                    addCreditDTO.obs(),
                    principal
            );

        } catch (Exception e) {
            throw new SoapIntegrationException("Erro ao comunicar com o serviço SOAP: " + getRootCauseMessage(e), e);
        }
    }

    private UserPrincipalDTO createUserPrincipal() {
        UserPrincipalDTO userPrincipal = new UserPrincipalDTO();

        var login = integration().getUsername();
        var password = cryptoService.decrypt(integration().getPassword());

        userPrincipal.setLogin(login);
        userPrincipal.setPassword(password);
        return userPrincipal;
    }
    
    private IntegrationEntity integration(){
        return integrationService.findByTypeAndStatus(IntegrationsType.SIPPULSE).orElseThrow(
                () -> new RuntimeException("IntegrationEntity configuration for SIPPULSE not found")
        );
    };
}
