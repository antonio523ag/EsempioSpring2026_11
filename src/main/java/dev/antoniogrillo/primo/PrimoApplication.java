package dev.antoniogrillo.primo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimoApplication {

    private static final Logger mioLogger=LogManager.getLogger();

    public static void main(String[] args) {
        mioLogger.info("Avvio applicazione");
        mioLogger.warn("Messaggio di avviso");
        mioLogger.fatal("Rotto tutto");
        mioLogger.error("Errore generico");
        mioLogger.debug("Debug");
        mioLogger.trace("Trace");
        SpringApplication.run(PrimoApplication.class, args);
    }

}
