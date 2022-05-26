package ru.taksebe.telegram.writeRead;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyStoreException;
import java.security.Security;

@SpringBootApplication
public class WriteReadApplication {

    public static void main(String[] args) throws KeyStoreException {
        PemKeyStoreProvider newProvider = new PemKeyStoreProvider();
        Security.addProvider(newProvider);
        SpringApplication.run(WriteReadApplication.class, args);
    }
}