package com.fullStackBE.journalApp.controller;

import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
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

    @GetMapping("id/{id}")
    public  ResponseEntity<JournalEntryDTO> getJournalByID(@RequestParam String id){
        return service.getJournalByID(id);
    }

    @DeleteMapping("id/{id}")
    public  ResponseEntity<String> deleteJournalByID(@RequestParam String id){
        return service.deleteJournalByID(id);
    }

    @PostMapping("id/{id}")
    public  ResponseEntity<JournalEntryDTO> updateJournalByID(
            @RequestParam String id, @RequestBody JournalEntryDTO journalEntry)
    {
        return service.updateJournalByID(id,journalEntry);
    }
}

