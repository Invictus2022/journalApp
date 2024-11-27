package com.fullStackBE.journalApp.service;


import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.dto.UserDTO;
import com.fullStackBE.journalApp.entity.JournalEntry;
import com.fullStackBE.journalApp.entity.User;
import com.fullStackBE.journalApp.mapper.JournalEntryMapper;
import com.fullStackBE.journalApp.repo.JournalEntryRepository;
import com.fullStackBE.journalApp.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class JournalEntryServiceImpl implements JournalEntryService {

    private static final Logger logger = LoggerFactory.getLogger(JournalEntryServiceImpl.class);
    private final JournalEntryRepository repository;
    private final UserRepository userRepository;



    public JournalEntryServiceImpl(JournalEntryRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;

    }

//    Save Journal using Username
    @Override
    public ResponseEntity<String> saveJournalEntry(String name, JournalEntryDTO journalEntry) {
        User fetchedUserByName = userRepository.findByUserName(name);

        if (fetchedUserByName != null){
            journalEntry.setDate(LocalDateTime.now());

            journalEntry.setUserId(fetchedUserByName.getId());

            JournalEntry journalEntryMapped = JournalEntryMapper
                    .INSTANCE
                    .mapJournalEntryDTOToJornalEntry(journalEntry);

            repository.save(journalEntryMapped);
            return new ResponseEntity<>("Done", HttpStatus.OK);
        }else {
            return  new ResponseEntity<>("Given name not found", HttpStatus.NOT_FOUND);
        }

    }


//    Fetch All Journal
    @Override
   public ResponseEntity<List<JournalEntryDTO>> getAllJournalEntry(){
        List<JournalEntry> journalEntry = repository.findAll();
        logger.info("Fetched journal entries: {}", journalEntry);

        List<JournalEntryDTO> journalEntryDTOS = JournalEntryMapper
                .INSTANCE
                .mapJournalEntryListToJournalEntryListDTO(journalEntry);

        return new ResponseEntity<>(journalEntryDTOS,HttpStatus.OK);
    }


//    Get JournalByID
    @Override
    public ResponseEntity<JournalEntryDTO> getJournalByID(String id){

       if (repository.findById(id).isPresent()){
           JournalEntry journalEntry =  repository.findById(id).get();
           return new ResponseEntity<>(
                   JournalEntryMapper
                           .INSTANCE
                           .mapJournalEntryToJornalEntryDTO(journalEntry), HttpStatus.OK);
       }else {
           return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
       }
    }

    //    Get JournalByUserID(Consumed by User Service)
    public List<JournalEntry> getJournalByUserID(String id){

        if (repository.findByUserId(id).isEmpty()){
            return null;
        }else {
            return repository.findByUserId(id);
        }
    }


//   Delete JournalByID
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


//   Update JournalByID
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
