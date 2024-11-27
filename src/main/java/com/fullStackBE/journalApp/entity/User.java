package com.fullStackBE.journalApp.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users" )
@Data
@Builder
public class User {

    @Id
    String id;

    @Indexed(unique = true)
    @NonNull
    private String userName;

    @NonNull
    private  String password;
    private String email;
    private boolean sentimentAnalysis;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> roles;
}
