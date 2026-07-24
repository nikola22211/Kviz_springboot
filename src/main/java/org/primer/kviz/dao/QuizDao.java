package org.primer.kviz.dao;

import org.primer.kviz.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface QuizDao extends JpaRepository<Quiz,Integer> {

    @Query("SELECT q FROM Quiz q JOIN FETCH q.questions WHERE q.id = :id")
    Optional<Quiz> findByIdWithQuestions(@Param("id") Integer id);
}
