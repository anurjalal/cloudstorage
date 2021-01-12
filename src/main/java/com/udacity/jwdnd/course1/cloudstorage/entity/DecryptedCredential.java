package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DecryptedCredential {
    private Integer credentialId;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decryptPassword;
    private Integer userid;

    public DecryptedCredential(Integer credentialId, String url, String username, String key, String password, String decryptPassword, Integer userid) {
        this.credentialId = credentialId;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
        this.decryptPassword = decryptPassword;
        this.userid = userid;
    }
}
