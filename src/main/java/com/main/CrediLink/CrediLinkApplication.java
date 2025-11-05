package com.main.CrediLink;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class CrediLinkApplication {

	public static void main(String[] args) {

        // 🔹 1. Reseta completamente o LogManager do JUL
        LogManager logManager = LogManager.getLogManager();
        logManager.reset();

        // 🔹 2. Desativa explicitamente todos os loggers SOAP/JAX-WS
        Logger.getLogger("com.sun.xml.ws").setLevel(Level.OFF);
        Logger.getLogger("com.sun.xml.ws.policy").setLevel(Level.OFF);
        Logger.getLogger("com.sun.xml.ws.policy.impl").setLevel(Level.OFF);
        Logger.getLogger("com.sun.xml.ws.policy.sourcemodel").setLevel(Level.OFF);
        Logger.getLogger("com.sun.xml.ws.policy.privateutil").setLevel(Level.OFF);
        Logger.getLogger("com.sun.xml.ws.policy.subject").setLevel(Level.OFF);
        Logger.getLogger("javax.xml.ws").setLevel(Level.OFF);
        Logger.getLogger("").setLevel(Level.SEVERE); // root JUL logger

		SLF4JBridgeHandler.removeHandlersForRootLogger();
		SLF4JBridgeHandler.install();
		SpringApplication.run(CrediLinkApplication.class, args);
	}

}


