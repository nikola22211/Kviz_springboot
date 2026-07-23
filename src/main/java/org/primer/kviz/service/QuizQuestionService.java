package org.primer.kviz.service;


import org.primer.kviz.dao.QuizQuestionsDao;
import org.primer.kviz.model.Question;
import org.primer.kviz.model.QuizQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizQuestionService {

    private final QuizQuestionsDao quizQuestionsDao;

    @Autowired
    public QuizQuestionService(QuizQuestionsDao quizQuestionsDao){
        this.quizQuestionsDao = quizQuestionsDao;
    }

    public List<QuizQuestions> vratiPitanja(Integer id){
        return quizQuestionsDao.pronadjiPoIdu(id); //OVDE PROVERITI
    }


}
