package com.fullStackBE.journalApp.service;


import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface JournalEntryService {
    ResponseEntity<String> saveJournalEntry(String name, JournalEntryDTO journalEntry);

    ResponseEntity<List<JournalEntryDTO>> getAllJournalEntry();

    ResponseEntity<JournalEntryDTO> getJournalByID(String id);

    ResponseEntity<String> deleteJournalByID(String id);


    ResponseEntity<String> updateJournalByID(String id, JournalEntryDTO journalEntry);
}
