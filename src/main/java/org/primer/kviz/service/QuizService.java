package org.primer.kviz.service;


import org.primer.kviz.dao.QuestionDao;
import org.primer.kviz.dao.QuizDao;
import org.primer.kviz.model.Question;
import org.primer.kviz.model.QuestionWrapper;
import org.primer.kviz.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private final QuizDao quizDao;
    private final QuestionDao questionDao;

    @Autowired
    private QuizService(QuizDao quizDao, QuestionDao questionDao){
        this.quizDao = quizDao;
        this.questionDao = questionDao;
    }


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);

        return ResponseEntity.status(HttpStatus.CREATED).body("success");
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id){
        Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questionsFromDB = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),
                    q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return ResponseEntity.status(HttpStatus.OK).body(questionsForUser);
    }
}
