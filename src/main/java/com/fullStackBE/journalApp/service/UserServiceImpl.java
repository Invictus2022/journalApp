package com.fullStackBE.journalApp.service;


import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.dto.UserDTO;
import com.fullStackBE.journalApp.entity.JournalEntry;
import com.fullStackBE.journalApp.entity.User;
import com.fullStackBE.journalApp.mapper.JournalEntryMapper;
import com.fullStackBE.journalApp.mapper.UserMapper;
import com.fullStackBE.journalApp.repo.JournalEntryRepository;
import com.fullStackBE.journalApp.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository repository;
    @Autowired
    private final  JournalEntryServiceImpl journalEntryService;

    @Autowired
    private  final JournalEntryRepository journalEntryRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository repository, JournalEntryServiceImpl journalEntryService, JournalEntryRepository journalEntryRepository) {
        this.repository = repository;
        this.journalEntryService = journalEntryService;
        this.journalEntryRepository = journalEntryRepository;
    }


    @Override
    public ResponseEntity<UserDTO> saveUser(UserDTO userDTO){
        User user = UserMapper.INSTANCE.mapUserDTOToUser(userDTO);
        repository.save(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    // Get User By Name with journal
    @Override
    public ResponseEntity<?> getUserByName(String name){
       User user = repository.findByUserName(name);

     List<JournalEntry> journalEntryByID = journalEntryService
              .getJournalByUserID(user.getId());

       user.setJournalEntries(journalEntryByID);

       UserDTO fetchedUser = UserMapper.INSTANCE.mapUserToUserDTO(user);
       return new ResponseEntity<>(fetchedUser,HttpStatus.OK);
    }

    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> userDTO = UserMapper.INSTANCE.mapUserToUserDTO(repository.findAll());
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
    }

    // Get UserById
    public ResponseEntity<UserDTO> getUserByID(String id){
        if (repository.findById(id).isPresent()){
            User user = repository.findById(id).get();

            logger.info(String.valueOf(user));

            List<JournalEntry> journalEntryByID = journalEntryService
                    .getJournalByUserID(id);

            logger.info(journalEntryByID.toString());

            user.setJournalEntries(journalEntryByID);

            UserDTO fetchedUser = UserMapper.INSTANCE.mapUserToUserDTO(user);
            return new ResponseEntity<>(fetchedUser,HttpStatus.OK);

        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    public ResponseEntity<UserDTO> updateUser(String id,UserDTO userDTO){
        if (repository.findById(id).isPresent()){
            User fetchedUser = repository.findById(id).get();
            User user = UserMapper.INSTANCE.mapUserDTOToUser(userDTO);

            fetchedUser.setUserName(user.getUserName());
            fetchedUser.setPassword(user.getPassword());
            fetchedUser.setRoles(user.getRoles());
            fetchedUser.setEmail(user.getEmail());
            fetchedUser.setJournalEntries(user.getJournalEntries());
            fetchedUser.setRoles(user.getRoles());

            repository.save(fetchedUser);
            UserDTO userDTO1  = UserMapper.INSTANCE.mapUserToUserDTO(fetchedUser);

            return new ResponseEntity<>(userDTO1, HttpStatus.OK);

        }else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete User
    public ResponseEntity<String> deleteUser(String  id){
        if (repository.findById(id).isPresent()){
            repository.deleteById(id);
            return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
        }else {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
}
