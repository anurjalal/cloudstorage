package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.entity.DecryptedCredential;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public void encrypt(Credential credential) {
        String sKey = encryptionService.getRandomKey();
        String encryptedPassword = encryptionService.encrypt(credential.getPassword(), sKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(sKey);
    }

    public String decryptPassword(Credential credential) {
        return encryptionService.decrypt(credential.getPassword(), credential.getKey());
    }

    public void addCredential(Credential credential) {
        encrypt(credential);
        Integer id = credential.getCredentialId();
        if (id == null) {
            credentialMapper.addCredential(credential);
        } else {
            credentialMapper.updateCredential(credential);
        }
    }

    public void deleteCredential(Integer id) {
        credentialMapper.deleteCredential(id);
    }

    public List<DecryptedCredential> getCredentialByUserId(Integer userId) {
        List<Credential> credentialList = credentialMapper.getAllCredentialByUserId(userId);
        ArrayList<DecryptedCredential> decryptedCredentials = new ArrayList<>();
        for (Credential credential : credentialList) {
            decryptedCredentials.add(getDecryptedCredential(credential));
        }
        return decryptedCredentials;
    }

    public DecryptedCredential getDecryptedCredential(Credential credential) {
        String decryptedPassword = decryptPassword(credential);
        return new DecryptedCredential(
                credential.getCredentialId(), credential.getUrl(), credential.getUsername(), credential.getKey(), credential.getPassword(), decryptedPassword, credential.getUserid());
    }
}
