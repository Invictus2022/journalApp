package com.fullStackBE.journalApp.controller;

import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.service.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journalapp")
public class JournalEntryController {

    @Autowired
    private JournalEntryService service;

    public JournalEntryController(JournalEntryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<String> saveJournalEntry(@RequestBody JournalEntryDTO journalEntry){
        return service.saveJournalEntry(journalEntry);
    }

    @GetMapping
    public ResponseEntity<List<JournalEntryDTO>> getAllJournalEntry(){
        return service.getAllJournalEntry();
    }
}

