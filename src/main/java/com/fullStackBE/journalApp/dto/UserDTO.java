package com.fullStackBE.journalApp.dto;

import com.fullStackBE.journalApp.entity.JournalEntry;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
        private String id;
        private String userName;
        private  String password;
        private String email;
        private boolean sentimentAnalysis;
        private List<JournalEntry> journalEntries = new ArrayList<>();
        private List<String> roles;
    }

