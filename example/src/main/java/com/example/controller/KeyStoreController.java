package com.example.controller;

import com.example.service.KeystoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;

@Slf4j
@RestController
public class KeyStoreController
{
    @Autowired
    private KeystoreService keystoreService;

    @RequestMapping(path = "/keystore", method = RequestMethod.POST)
    void postvoid(@RequestBody @NotNull final MultipartFile entity,
                  @RequestParam(required = false) final String keyStorePassword, @RequestParam final String certAlias,
                  @RequestParam(required = false) final String certificatePassword)
    {
        try
        {
            keystoreService.uploadKeystore(entity.getInputStream(), keyStorePassword, certAlias, certificatePassword);
        }
        catch (KeyStoreException | UnrecoverableEntryException | NoSuchAlgorithmException | CertificateException | IOException e)
        {
            e.printStackTrace();
        }
    }
}
