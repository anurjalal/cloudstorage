package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String handleFileUpload(Credential credential, Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        credential.setUserid(userId);
        credentialService.addCredential(credential);
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/delete")
    public String handleCredentialDelete(@RequestParam("id") String id, Model model) {
        Integer credentialId = Integer.valueOf(id);
        credentialService.deleteCredential(credentialId);
        model.addAttribute("success", true);
        return "result";
    }
}
