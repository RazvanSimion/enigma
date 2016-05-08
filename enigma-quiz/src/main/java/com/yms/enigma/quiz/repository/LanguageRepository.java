package com.yms.enigma.quiz.repository;

import com.yms.enigma.quiz.domain.Language;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Language entity.
 */
public interface LanguageRepository extends JpaRepository<Language,Long> {

}
