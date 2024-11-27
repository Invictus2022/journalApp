package com.fullStackBE.journalApp.mapper;


import com.fullStackBE.journalApp.dto.JournalEntryDTO;
import com.fullStackBE.journalApp.entity.JournalEntry;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface JournalEntryMapper {

    JournalEntryMapper INSTANCE = Mappers.getMapper(JournalEntryMapper.class);


    JournalEntryDTO mapJournalEntryToJornalEntryDTO(JournalEntry journalEntry);
    JournalEntry mapJournalEntryDTOToJornalEntry(JournalEntryDTO journalEntryDTO);

    List<JournalEntryDTO> mapJournalEntryListToJournalEntryListDTO(List<JournalEntry> journalEntry);

    List<JournalEntry> mapJournalEntryListDTOToJournalEntryList(List<JournalEntryDTO> journalEntry);

}
