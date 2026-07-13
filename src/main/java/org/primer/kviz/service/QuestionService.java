package org.primer.kviz.service;


import org.primer.kviz.model.Question;
import org.primer.kviz.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionDao questionDao;

    @Autowired
    public QuestionService(QuestionDao qdao){
        this.questionDao = qdao;
    }

    public ResponseEntity<List<Question>> getAllQuestions(){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(questionDao.findAll());
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ArrayList<>());
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category){
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).
                    body(questionDao.findByCategory(category));
        }catch(Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).
                body(new ArrayList<>());
    }

    public ResponseEntity<String> addQuestion(Question question){
        questionDao.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success");
    }

    public ResponseEntity<String> deleteQuestion(int id){
        questionDao.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success");
    }

    public ResponseEntity<Question> getQuestionById(int id){
        Optional<Question> question = questionDao.findById(id);
        if(question.isPresent()){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(question.get());
        }
        return ResponseEntity.notFound().build();
    }
}
