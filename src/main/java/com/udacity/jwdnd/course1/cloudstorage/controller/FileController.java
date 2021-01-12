package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {

    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String createFile(@RequestParam("file") MultipartFile file, File fileUpload, Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        if (fileService.isFilenameAlreadyTaken(file.getOriginalFilename(), userId)) {
            model.addAttribute("filenameError", true);
            return "result";
        } else {
            try {
                fileUpload.setData(file.getInputStream().readAllBytes());
                fileUpload.setContentType(file.getContentType());
                fileUpload.setName(file.getOriginalFilename());
                fileUpload.setSize(String.valueOf(file.getSize()));
                fileUpload.setUserid(userId);
            } catch (IOException e) {
                model.addAttribute("success", false);
                e.printStackTrace();
                return "result";
            }
        }

        Integer rawAffected = fileService.createFile(fileUpload);
        if (rawAffected < 1) model.addAttribute("success", false);
        else model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/download/{id}")
    @ResponseBody
    public ResponseEntity<?> getFileById(@PathVariable(name = "id") String idS, Authentication authentication) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        Integer id = Integer.valueOf(idS);
        File file = fileService.getFile(id, userId);
        ByteArrayResource resource = new ByteArrayResource(file.getData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.valueOf(file.getContentType()))
                .contentLength(Long.parseLong(file.getSize()))
                .body(resource);
    }

    @GetMapping("/delete")
    public String deleteFile(@RequestParam("id") String idS, Model model) {
        Integer id = Integer.valueOf(idS);
        Integer rawAffected = fileService.deleteFile(id);
        if (rawAffected < 1) model.addAttribute("success", false);
        else model.addAttribute("success", true);
        return "result";
    }
}
