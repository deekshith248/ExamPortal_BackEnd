package com.exam.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@RequestBody Question question){
		return ResponseEntity.ok(this.questionService.updateQuestion(question));
	}
	
	@GetMapping("/{quesId}")
	public Question getQuestion(@PathVariable("quesId") Long  quesId) {
		return this.questionService.getQuestion(quesId);
	}
	
	@GetMapping("/quiz/all/{qid}")
	public ResponseEntity<?> getQuestionofQuizAdmin(@PathVariable("qid") Long qid){
		
		Quiz quiz=new Quiz();
		quiz.setQid(qid);
		Set<Question> ques=this.questionService.getQuestionofQuiz(quiz);
	
		return ResponseEntity.ok(ques);		
		
	}
	
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionofQuiz(@PathVariable("qid") Long qid){
		
		Quiz quiz=this.quizService.getQuiz(qid);
		Set<Question> questions=quiz.getQuestions();
		List<Question> list=new ArrayList<>(questions);
		if(list.size()>quiz.getNumberOfQuestions()) {
			list.subList(0, (int) (quiz.getNumberOfQuestions()+1));
		}
		list.forEach(q->{
			q.setAnswer("");
		});
		Collections.shuffle(list);
		return ResponseEntity.ok(list);		
		
	}
	
	@DeleteMapping("/{quesId}")
	public void deleteQuestiion(@PathVariable("quesId") Long quesId){
		this.questionService.deleteQuestion(quesId);
		
	}
	
	@SuppressWarnings("null")
	@PostMapping("/evaluate")
	public ResponseEntity<?> evaluateQuizafterSubmission(@RequestBody List<Question> questions) {

		double marksGot = 0;
		int correctAnswers = 0;
		int attempted = 0;
		for (Question q : questions) {
//			Question question = questionService.getQuesByQuesId(q.getQuesId());
			if(q.getGivenAnswer()!=null) {
			if (q.getGivenAnswer().trim().equals(q.getAnswer())) {
				correctAnswers += 1;
				marksGot += questions.get(0).getQuiz().getMaxMarks() / questions.size();
			}
			if (!q.getGivenAnswer().trim().equals("") ) {
				attempted += 1;
			}
		}
		}
		
		Map<String,Object> map=new HashMap<>();
		map.put("marksGot",marksGot);
		map.put("correctAnswers",correctAnswers);
		map.put("attempted",attempted);
		

		return ResponseEntity.ok(map);

	}
	

	
}
