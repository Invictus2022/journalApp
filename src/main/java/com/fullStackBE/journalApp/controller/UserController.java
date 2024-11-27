package com.fullStackBE.journalApp.controller;



import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.dto.UserDTO;
import com.fullStackBE.journalApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return service.saveUser(userDTO);
    }

    @GetMapping("name/{name}")
    public ResponseEntity<?> getUserByName(@PathVariable String name){
        return service.getUserByName(name);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return service.getAllUsers();
    }

    @GetMapping("id/{id}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable String id){
        return service.getUserByID(id);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id ,@RequestBody UserDTO userDTO){
        return service.updateUser(id,userDTO);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String  id){
        return service.deleteUser(id);
    }

}
