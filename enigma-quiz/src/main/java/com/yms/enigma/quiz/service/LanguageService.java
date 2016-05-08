package com.yms.enigma.quiz.service;

import com.yms.enigma.quiz.domain.Language;
import com.yms.enigma.quiz.repository.LanguageRepository;
import com.yms.enigma.quiz.repository.search.LanguageSearchRepository;
import com.yms.enigma.quiz.web.rest.dto.LanguageDTO;
import com.yms.enigma.quiz.web.rest.mapper.LanguageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Language.
 */
@Service
@Transactional
public class LanguageService {

    private final Logger log = LoggerFactory.getLogger(LanguageService.class);
    
    @Inject
    private LanguageRepository languageRepository;
    
    @Inject
    private LanguageMapper languageMapper;
    
    @Inject
    private LanguageSearchRepository languageSearchRepository;
    
    /**
     * Save a language.
     * 
     * @param languageDTO the entity to save
     * @return the persisted entity
     */
    public LanguageDTO save(LanguageDTO languageDTO) {
        log.debug("Request to save Language : {}", languageDTO);
        Language language = languageMapper.languageDTOToLanguage(languageDTO);
        language = languageRepository.save(language);
        LanguageDTO result = languageMapper.languageToLanguageDTO(language);
        languageSearchRepository.save(language);
        return result;
    }

    /**
     *  Get all the languages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Language> findAll(Pageable pageable) {
        log.debug("Request to get all Languages");
        Page<Language> result = languageRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one language by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public LanguageDTO findOne(Long id) {
        log.debug("Request to get Language : {}", id);
        Language language = languageRepository.findOne(id);
        LanguageDTO languageDTO = languageMapper.languageToLanguageDTO(language);
        return languageDTO;
    }

    /**
     *  Delete the  language by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Language : {}", id);
        languageRepository.delete(id);
        languageSearchRepository.delete(id);
    }

    /**
     * Search for the language corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Language> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Languages for query {}", query);
        return languageSearchRepository.search(queryStringQuery(query), pageable);
    }
}
