package com.example.todolist.service;

import com.example.todolist.model.Note;
import com.example.todolist.repository.NoteRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
        noteRepository.save(note);
    }

    public Note getById(long id) {
        return noteRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Note with ID " + id + " not found"));
    }

    @PostConstruct
    public void init() {
        add(new Note(1L, "First Note", "This is the content of the first note."));
        add(new Note(2L, "Second Note", "This is the content of the second note."));
        add(new Note(3L, "Third Note", "This is the content of the third note."));
    }
}
