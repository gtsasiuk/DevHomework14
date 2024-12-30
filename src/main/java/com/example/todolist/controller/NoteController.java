package com.example.todolist.controller;

import com.example.todolist.model.Note;
import com.example.todolist.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping("/create")
    public String createNote(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";
    }

    @GetMapping("/create")
    public String getCreateNotePage(Model model) {
        model.addAttribute("note", new Note());
        return "note/create";
    }

    @GetMapping("/list")
    public String getAllNotes(Model model) {
        List<Note> notes = noteService.listAll();
        model.addAttribute("notes", notes);
        return "note/list";
    }

    @GetMapping("/edit")
    public String getNoteEditPage(@RequestParam Long id, Model model) {
        try {
            Note note = noteService.getById(id);
            model.addAttribute("note", note);
            return "note/edit";
        } catch (NoSuchElementException e) {
            return "redirect:/note/list?error=Note+not+found";
        }
    }

    @PostMapping("/edit")
    public String editNote(@ModelAttribute Note note) {
        try {
            noteService.update(note);
        } catch (NoSuchElementException e) {
            return "redirect:/note/list?error=Note+not+found";
        }
        return "redirect:/note/list";
    }

    @PostMapping("/delete")
    public String deleteNote(@RequestParam Long id) {
        try {
            noteService.deleteById(id);
        } catch (NoSuchElementException e) {
            return "redirect:/note/list?error=Note+not+found";
        }
        return "redirect:/note/list";
    }

}
