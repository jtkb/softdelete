package com.example.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

public interface KeystoreService
{
    String PKCS12 = "pkcs12";

    String JKS = "jks";

    void uploadKeystore(InputStream keystoreInputStream, String keyStorePassword, final String certAlias, String certificatePassword) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException;
}
