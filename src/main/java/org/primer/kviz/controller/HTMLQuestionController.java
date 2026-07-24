package org.primer.kviz.controller;


import org.primer.kviz.dao.QuizDao;
import org.primer.kviz.model.Quiz;
import org.primer.kviz.model.Statistics;
import org.primer.kviz.model.Question;
import org.primer.kviz.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class HTMLQuestionController {

    private final QuestionService questionService;
    private List<Question> fiveQuestions;
    private Statistics statistics;
    private final QuizDao quizDao;

    @Autowired
    public HTMLQuestionController(QuestionService qs, Statistics statistics,QuizDao quizDao){
        questionService = qs;
        this.statistics = statistics;
        this.quizDao = quizDao;
    }

    @GetMapping("/")
    public String getIntro(Model model){
        model.addAttribute("score",statistics.getRightAnswers());
        return "question";
    }

    @GetMapping("/allQuestions")
    public String getAllQuestions(Model model){
        List<Question> questions = questionService.getAllQuestions().getBody();
        model.addAttribute("questions",questions);
        return "allQuestions";
    }

    @GetMapping("/quiz/{id}")
    public String getQuiz(@PathVariable(name="id")Integer id, Model model) {

        Quiz quiz = quizDao.findById(id).orElseThrow();

        List<Question> questions = quiz.getQuestions();

        model.addAttribute("questions", questions);

        return "quiz";
    }
       /* Random r = new Random();
        List<Question> questions = questionService.getAllQuestions().getBody();
        fiveQuestions = new ArrayList<>();
        List<Integer> randbrojevi = new ArrayList<>();
        for(int i=0;i<5;){
            int br = r.nextInt(0,26);
            if(randbrojevi.contains(br)){
                continue;
            }
            fiveQuestions.add(questions.get(br));
            randbrojevi.add(br);
            i++;
        }
        model.addAttribute("fiveQuestions",fiveQuestions);
        return "quiz";
    }*/


   /* Ukratko

    @RequestParam čita parametre zahteva, bez obzira da li dolaze:

    iz URL-a (GET):

            /person?name=Jane

    ili iz HTML forme (POST sa application/x-www-form-urlencoded):

    name=Jane

    Oba se u Spring MVC tretiraju kao request parameters.*/
    @PostMapping("/quiz/submit")    //Ti ne praviš ovu mapu ručno. Spring je popuni na osnovu podataka iz forme.
    public String submitQuiz(@RequestParam Map<String,String> mapa, Model model) {
        statistics.setVisit(statistics.getVisit()+1);
        statistics.setRightAnswers(0);
        for(Map.Entry<String,String> entry : mapa.entrySet()) {

            int questionId = Integer.parseInt(entry.getKey());
            String selectedAnswer = entry.getValue();

            Question question = questionService.getQuestionById(questionId).getBody();

            if (question != null &&
                    question.getRightAnswer().equals(selectedAnswer)) {
                statistics.setRightAnswers(statistics.getRightAnswers()+1);
            }
        }

        statistics.getSolved().add(statistics.getRightAnswers());
        model.addAttribute("score", statistics.getRightAnswers());
        return "submit";
    }



}
