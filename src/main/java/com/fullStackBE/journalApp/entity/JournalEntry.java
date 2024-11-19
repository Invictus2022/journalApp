package com.fullStackBE.journalApp.entity;


import com.fullStackBE.journalApp.model.SentimentData;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JournalEntry {

        @Id
        private ObjectId id;
        @NonNull
        private String title;
        private String content;
        private LocalDateTime date;
        private SentimentData sentiment;


}
