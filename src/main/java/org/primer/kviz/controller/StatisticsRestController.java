package org.primer.kviz.controller;


import jakarta.persistence.criteria.CriteriaBuilder;
import org.primer.kviz.model.Statistics;
import org.primer.kviz.service.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsRestController {

    private final QuestionService questionService;
    private Statistics statistics;

    public StatisticsRestController(QuestionService questionService, Statistics statistics){
        this.questionService = questionService;
        this.statistics = statistics;
    }

    @GetMapping
    public String getStatistics(){
        int numOfQuestions = questionService.getAllQuestions().getBody().size();
        double avg = 0;
        for(Integer k : statistics.getSolved()){
            avg += ((double)k/5)/statistics.getVisit();
        }
        return "Broj pitanja: " + numOfQuestions + " \nBroj resavanja kviza je: " + statistics.getVisit() + "\nProsek" +
                " je: " + avg;

    }



}
