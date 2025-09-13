package com.main.CrediLink.sippulse.services;

import com.main.CrediLink.domain.integrations.entity.IntegrationEntity;
import com.main.CrediLink.domain.integrations.enums.IntegrationsType;
import com.main.CrediLink.domain.integrations.service.IntegrationService;
import com.main.CrediLink.exceptions.SoapIntegrationException;
import com.main.CrediLink.sippulse.dto.AddCreditDTO;
import com.main.CrediLink.sippulse.wsdlSippulse.SipPulse;
import com.main.CrediLink.sippulse.wsdlSippulse.SubscriberWS;
import com.main.CrediLink.sippulse.wsdlSippulse.UserPrincipalDTO;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

import static org.apache.commons.lang3.exception.ExceptionUtils.getRootCauseMessage;

@Service
public class SipPulseService {

    private final IntegrationService integrationService;

    public SipPulseService(IntegrationService integrationService) {
        this.integrationService = integrationService;
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
        userPrincipal.setLogin(integration().getUsername());
        userPrincipal.setPassword(integration().getPassword());
        return userPrincipal;
    }
    
    private IntegrationEntity integration(){
        return integrationService.findByType(IntegrationsType.SIPPULSE).orElseThrow(
                () -> new RuntimeException("IntegrationEntity configuration for SIPPULSE not found")
        );
    };
}
