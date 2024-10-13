package com.exam.services.implementaion;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Question;
import com.exam.model.Quiz;
import com.exam.repository.QuestionRepository;
import com.exam.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		// TODO Auto-generated method stub
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Question question) {
		
		return this.questionRepository.save(question);
	}

	@Override
	public  List<Question> getQuestionsList() {
		return  this.questionRepository.findAll();
	}

	@Override
	public Question getQuestion(Long quesId) {
		
		return questionRepository.findById(quesId).get();
	}

	@Override
	public Set<Question> getQuestionofQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return questionRepository.findByQuiz(quiz);
	}

	@Override
	public void deleteQuestion(Long quesId) {
		 this.questionRepository.deleteById(quesId);
		
	}

	@Override
	public Question getQuesByQuesId(Long quesId) {
		return this.questionRepository.getById(quesId);
		
	}

}
