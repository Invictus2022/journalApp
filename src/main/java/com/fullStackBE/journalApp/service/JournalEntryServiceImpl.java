package com.fullStackBE.journalApp.service;


import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.entity.JournalEntry;
import com.fullStackBE.journalApp.mapper.JournalEntryMapper;
import com.fullStackBE.journalApp.repo.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryServiceImpl.class);
    private final JournalEntryRepository repository;

    public JournalEntryServiceImpl(JournalEntryRepository repository) {
        this.repository = repository;
    }


    @Override
    public ResponseEntity<String> saveJournalEntry(JournalEntryDTO journalEntry) {
        journalEntry.setDate(LocalDateTime.now());

        JournalEntry journalEntryMapped = JournalEntryMapper.INSTANCE.mapJournalEntryDTOToJornalEntry(journalEntry);
        repository.save(journalEntryMapped);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }


    @Override
   public ResponseEntity<List<JournalEntryDTO>> getAllJournalEntry(){
        List<JournalEntry> journalEntry = repository.findAll();
        logger.info("Fetched journal entries: {}", journalEntry);

        List<JournalEntryDTO> journalEntryDTOS = JournalEntryMapper
                .INSTANCE
                .mapJournalEntryListToJournalEntryListDTO(journalEntry);

        return new ResponseEntity<>(journalEntryDTOS,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<JournalEntryDTO> getJournalByID(String id){

       if (repository.findById(id).isPresent()){
           JournalEntry journalEntry = repository.findById(id).get();
           return new ResponseEntity<>(
                   JournalEntryMapper
                           .INSTANCE
                           .mapJournalEntryToJornalEntryDTO(journalEntry), HttpStatus.OK);
       }else {
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       }
    }

    @Override
    public ResponseEntity<String> deleteJournalByID(String id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>(
                    "Deleted Successfully", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<String> updateJournalByID(String id, JournalEntryDTO journalEntry){
        if (repository.findById(id).isPresent()){
            JournalEntry convertJournalEntry = JournalEntryMapper
                    .INSTANCE
                    .mapJournalEntryDTOToJornalEntry(journalEntry);

            JournalEntry fetchJournalEntry =  repository.findById(id).get();

            fetchJournalEntry.setContent(convertJournalEntry.getContent());
            fetchJournalEntry.setTitle(convertJournalEntry.getTitle());
            fetchJournalEntry.setSentiment(convertJournalEntry.getSentiment());
            fetchJournalEntry.setDate(LocalDateTime.now());

            repository.save(fetchJournalEntry);

            return new ResponseEntity<>(
                    "Update Successful", HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
