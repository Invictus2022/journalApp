package com.fullStackBE.journalApp.controller;

import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/journalapp")
public class JournalEntryController {

    @Autowired
    private final JournalEntryService service;

    public JournalEntryController(JournalEntryService service) {
        this.service = service;
    }


    @PostMapping("{name}")
    public ResponseEntity<String> saveJournalEntry(@PathVariable String name ,@RequestBody JournalEntryDTO journalEntry){
        return service.saveJournalEntry(name,journalEntry);
    }

    @GetMapping
    public ResponseEntity<List<JournalEntryDTO>> getAllJournalEntry(){
        return service.getAllJournalEntry();
    }

    @GetMapping("id/{id}")
    public  ResponseEntity<JournalEntryDTO> getJournalByID(@PathVariable  String id){
        return service.getJournalByID(id);
    }

    @DeleteMapping("id/{id}")
    public  ResponseEntity<String> deleteJournalByID(@PathVariable  String id){
        return service.deleteJournalByID(id);
    }

    @PutMapping("id/{id}")
    public  ResponseEntity<String> updateJournalByID(
            @PathVariable  String id, @RequestBody JournalEntryDTO journalEntry)
    {
        return service.updateJournalByID(id,journalEntry);
    }


}

