package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public Integer createNote(Note note) {
        Integer noteId = note.getNoteId();
        if (noteId == null) {
            return noteMapper.save(note);
        } else {
            if(noteMapper.findById(note.getNoteId())!= null){
                return noteMapper.update(note);
            }else{
                return noteMapper.save(note);
            }
        }
    }

    public List<Note> getAllNotes(Integer userId) {
        return noteMapper.findAllByUserId(userId);
    }

    public Integer deleteNote(Integer noteId) {
        return noteMapper.delete(noteId);
    }
}
