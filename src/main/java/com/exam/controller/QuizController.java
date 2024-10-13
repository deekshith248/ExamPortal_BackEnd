package com.exam.controller;

import java.util.List;

import javax.annotation.Generated;

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

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.services.QuizService;

@CrossOrigin(origins="*",allowedHeaders="*")
@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private QuizService quizService;
	
	@PostMapping("/")
	public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.addQuiz(quiz));
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
		return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllQuizzes() {
		return ResponseEntity.ok(quizService.getQuizzes());
		
	}
	
	@GetMapping("/{qid}")
	public Quiz getQuiz(@PathVariable("qid")Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	@DeleteMapping("/{qid}")
	public void delQuiz(@PathVariable("qid") Long qid) {
		 this.quizService.deleteQuiz(qid);
	}
	
	@GetMapping("/category/{cid}")
	public List<Quiz> getQuizzesofCategory(@PathVariable Long cid){
		Category cat=new Category();
		cat.setCategoryId(cid);
		return this.quizService.getQuizzesofcategory(cat);
	}
	
	@GetMapping("/active")
	public List<Quiz> getActiveQuizzes() {
		return this.quizService.getActiveQuizzes();
	}
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizzesofCategory(@PathVariable Long cid){
		Category cat=new Category();
		cat.setCategoryId(cid);
		return this.quizService.getActiveQuizzesofCategory(cat);
	}
	
	

}
