package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public String createNote(Note note, Authentication authentication, Model model) {
        Integer userId = userService.getUser(authentication.getName()).getUserId();
        note.setUserId(userId);
        Integer rowAffected = noteService.createNote(note);
        if (rowAffected < 1) model.addAttribute("success", false);
        else model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/delete")
    public String deleteNote(@RequestParam("id") String id, Model model) {
        Integer noteId = Integer.valueOf(id);
        Integer rowAffected = noteService.deleteNote(noteId);
        if (rowAffected < 1) model.addAttribute("success", false);
        else model.addAttribute("success", true);
        return "result";
    }
}
