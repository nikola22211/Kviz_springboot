package org.primer.kviz.dao;

import org.primer.kviz.model.Question;
import org.primer.kviz.model.QuizQuestions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizQuestionsDao extends JpaRepository<QuizQuestions,Integer> {

    @Query(value = "SELECT * FROM quiz_questions WHERE quiz_id = :id",nativeQuery = true)
    List<QuizQuestions> pronadjiPoIdu(Integer id);
}
