package com.fullStackBE.journalApp.repo;


import com.fullStackBE.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
    List<JournalEntry> findByUserId(String userID);
}
