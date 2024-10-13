package com.exam.services;

import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.exam.model.Question;
import com.exam.model.Quiz;

public interface QuestionService {
	
	public Question addQuestion (Question question);
	
	public Question updateQuestion(Question question);
	
	public List<Question> getQuestionsList();
	
	public Question getQuestion(Long quesId);
	
	public Set<Question> getQuestionofQuiz(Quiz quiz);
	
	public void deleteQuestion(Long quesId);

	public Question getQuesByQuesId(Long quesId);


	
	
	
	

}
