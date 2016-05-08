package com.yms.enigma.quiz.service;

import com.yms.enigma.quiz.domain.Answer;
import com.yms.enigma.quiz.repository.AnswerRepository;
import com.yms.enigma.quiz.repository.search.AnswerSearchRepository;
import com.yms.enigma.quiz.web.rest.dto.AnswerDTO;
import com.yms.enigma.quiz.web.rest.mapper.AnswerMapper;
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
 * Service Implementation for managing Answer.
 */
@Service
@Transactional
public class AnswerService {

    private final Logger log = LoggerFactory.getLogger(AnswerService.class);
    
    @Inject
    private AnswerRepository answerRepository;
    
    @Inject
    private AnswerMapper answerMapper;
    
    @Inject
    private AnswerSearchRepository answerSearchRepository;
    
    /**
     * Save a answer.
     * 
     * @param answerDTO the entity to save
     * @return the persisted entity
     */
    public AnswerDTO save(AnswerDTO answerDTO) {
        log.debug("Request to save Answer : {}", answerDTO);
        Answer answer = answerMapper.answerDTOToAnswer(answerDTO);
        answer = answerRepository.save(answer);
        AnswerDTO result = answerMapper.answerToAnswerDTO(answer);
        answerSearchRepository.save(answer);
        return result;
    }

    /**
     *  Get all the answers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Answer> findAll(Pageable pageable) {
        log.debug("Request to get all Answers");
        Page<Answer> result = answerRepository.findAll(pageable); 
        return result;
    }

    /**
     *  Get one answer by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public AnswerDTO findOne(Long id) {
        log.debug("Request to get Answer : {}", id);
        Answer answer = answerRepository.findOne(id);
        AnswerDTO answerDTO = answerMapper.answerToAnswerDTO(answer);
        return answerDTO;
    }

    /**
     *  Delete the  answer by id.
     *  
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Answer : {}", id);
        answerRepository.delete(id);
        answerSearchRepository.delete(id);
    }

    /**
     * Search for the answer corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<Answer> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Answers for query {}", query);
        return answerSearchRepository.search(queryStringQuery(query), pageable);
    }
}
