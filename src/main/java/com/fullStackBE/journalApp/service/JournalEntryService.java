package com.fullStackBE.journalApp.service;


import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface JournalEntryService {
    ResponseEntity<String> saveJournalEntry(JournalEntryDTO journalEntry);

    ResponseEntity<List<JournalEntryDTO>> getAllJournalEntry();
}
