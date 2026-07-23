package org.primer.kviz.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "quiz_questions")
public class QuizQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id")
    private Integer quiz_id;
    @Column(name = "questions_id")
    private Integer questions_id;
}
