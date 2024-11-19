package com.fullStackBE.journalApp.dto;


import com.fullStackBE.journalApp.model.SentimentData;
import lombok.*;
import org.bson.types.ObjectId;
import java.time.LocalDateTime;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public class JournalEntryDTO {
        private ObjectId id;
        private String title;
        private String content;
        private LocalDateTime date;
        private SentimentData sentiment;
    }

