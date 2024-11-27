package com.fullStackBE.journalApp.service;

import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDTO> saveUser(UserDTO userDTO);

    ResponseEntity<?> getUserByName(String name);

    ResponseEntity<List<UserDTO>> getAllUsers();

    ResponseEntity<UserDTO> getUserByID(String  id);

    ResponseEntity<UserDTO> updateUser(String id,UserDTO userDTO);

    ResponseEntity<String> deleteUser(String id);

}
