package com.fullStackBE.journalApp.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fullStackBE.journalApp.model.SentimentData;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JournalEntry {

        @Id
        private String id;

        @NonNull
        private String title;
        private String content;

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
        private LocalDateTime date;
        private SentimentData sentiment;

        @NonNull
        private String userId;

}
