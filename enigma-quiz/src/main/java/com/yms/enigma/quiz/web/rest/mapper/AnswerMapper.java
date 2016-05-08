package com.yms.enigma.quiz.web.rest.mapper;

import com.yms.enigma.quiz.domain.*;
import com.yms.enigma.quiz.web.rest.dto.AnswerDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Answer and its DTO AnswerDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AnswerMapper {

    @Mapping(source = "question.id", target = "questionId")
    AnswerDTO answerToAnswerDTO(Answer answer);

    List<AnswerDTO> answersToAnswerDTOs(List<Answer> answers);

    @Mapping(source = "questionId", target = "question")
    Answer answerDTOToAnswer(AnswerDTO answerDTO);

    List<Answer> answerDTOsToAnswers(List<AnswerDTO> answerDTOs);

    default Question questionFromId(Long id) {
        if (id == null) {
            return null;
        }
        Question question = new Question();
        question.setId(id);
        return question;
    }
}
