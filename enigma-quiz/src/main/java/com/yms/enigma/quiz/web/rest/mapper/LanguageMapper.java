package com.yms.enigma.quiz.web.rest.mapper;

import com.yms.enigma.quiz.domain.*;
import com.yms.enigma.quiz.web.rest.dto.LanguageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Language and its DTO LanguageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LanguageMapper {

    LanguageDTO languageToLanguageDTO(Language language);

    List<LanguageDTO> languagesToLanguageDTOs(List<Language> languages);

    @Mapping(target = "questions", ignore = true)
    Language languageDTOToLanguage(LanguageDTO languageDTO);

    List<Language> languageDTOsToLanguages(List<LanguageDTO> languageDTOs);
}
