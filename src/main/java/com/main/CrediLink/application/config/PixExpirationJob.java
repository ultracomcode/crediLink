package com.main.CrediLink.application.config;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

@Component
public class PixExpirationJob implements Job {

    @Override
    public void execute(JobExecutionContext context) {
        String clienteId = context.getJobDetail().getJobDataMap().getString("clienteId");

        System.out.println("Executando consulta mensal para o cliente: " + clienteId);
        // aqui você chama seu serviço:
        // consultaService.executarConsulta(clienteId);
    }
}
