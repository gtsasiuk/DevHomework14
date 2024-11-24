package com.example.todolist.service;

import com.example.todolist.model.Note;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new HashMap<Long, Note>();
    private final Random random = new Random();

    public List<Note> listAll() {
        return new ArrayList<Note>(notes.values());
    }

    public Note add(Note note) {
        long id = generateUniqueId();
        note.setId(id);
        notes.put(id, note);
        return note;
    }

    public void deleteById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with ID " + id + " not found");
        }
        notes.remove(id);
    }

    public void update(Note note) {
        if (!notes.containsKey(note.getId())) {
            throw new NoSuchElementException("Note with ID " + note.getId() + " not found");
        }
        Note existingNote = notes.get(note.getId());
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
    }

    public Note getById(long id) {
        if (!notes.containsKey(id)) {
            throw new NoSuchElementException("Note with ID " + id + " not found");
        }
        return notes.get(id);
    }

    private long generateUniqueId() {
        long id;
        do {
            id = random.nextLong();
        } while (notes.containsKey(id));
        return id;
    }
}
