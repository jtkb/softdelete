package com.example.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@Slf4j
@Service
public class KeystoreServiceImpl implements KeystoreService
{

    @Value("#{systemEnvironment['HOME']}/testkeystore/testkeystore.pkcs12")
    private String keyStoreLocation;

    private static final String KEYSTORE_PASS = "nopassword";

    @PostConstruct
    public void postConstruct() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException
    {
        final File keyStoreFile = new File(keyStoreLocation);
        if ( ! keyStoreFile.exists())
        {
            createKeyStore();
        }
    }

    @Override
    @Transactional
    public void uploadKeystore(final InputStream keystoreInputStream,
                               final String keyStorePassword, final String certAlias, final String certificatePassword)
            throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException
    {
        // Load the incoming keystore
        final KeyStore newKeyStore = KeyStore.getInstance(KeystoreService.PKCS12);
        newKeyStore.load(keystoreInputStream, keyStorePassword.toCharArray());
        // Test the incoming key is the correct type
        if ( ! newKeyStore.entryInstanceOf(certAlias, KeyStore.PrivateKeyEntry.class))
        {
            throw new RuntimeException("Incoming signing key is not Private key type.");
        }
        final KeyStore.PrivateKeyEntry incomingPrivateKey =
                (KeyStore.PrivateKeyEntry) newKeyStore.getEntry(certAlias, new KeyStore.PasswordProtection(certificatePassword.toCharArray()));

        // Open local keystore
        final Path localKeyStorePath = FileSystems.getDefault().getPath(keyStoreLocation);
        final KeyStore localKeyStore = KeyStore.getInstance(KeystoreService.PKCS12);
        localKeyStore.load(Files.newInputStream(localKeyStorePath, StandardOpenOption.READ), KEYSTORE_PASS.toCharArray());

        // Check if the proposed new alias already exists locally
        if (localKeyStore.isKeyEntry(certAlias) || localKeyStore.isCertificateEntry(certAlias))
        {
            throw new RuntimeException(String.format("New alias '%s' already exists", certAlias));
        }

        // Write new certificate to the local keystore.
        localKeyStore.setEntry(certAlias, incomingPrivateKey, new KeyStore.PasswordProtection(certificatePassword.toCharArray()));
        localKeyStore.store(Files.newOutputStream(localKeyStorePath, StandardOpenOption.WRITE), KEYSTORE_PASS.toCharArray());

    }

    private void createKeyStore() throws IOException, KeyStoreException, CertificateException, NoSuchAlgorithmException
    {
        final Path keyStorePath = Files.createFile(FileSystems.getDefault().getPath(keyStoreLocation));
        final FileOutputStream outputStream = new FileOutputStream(keyStorePath.toFile());

        final KeyStore keyStore = KeyStore.getInstance(KeystoreService.PKCS12);
        keyStore.load(null, null);
        keyStore.store(outputStream, KEYSTORE_PASS.toCharArray());
        log.info("KeyStore created.");
        outputStream.close();
    }
}
