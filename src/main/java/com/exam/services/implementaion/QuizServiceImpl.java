package com.exam.services.implementaion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.model.Quiz;
import com.exam.repository.QuizRepository;
import com.exam.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {
	
	@Autowired
	QuizRepository quizRepository;

	@Override
	public Quiz addQuiz(Quiz quiz) {
		
		return quizRepository.save(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		
		return quizRepository.save(quiz);
	}

	@Override
	public   List<Quiz> getQuizzes() {
		return quizRepository.findAll();
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		
		return quizRepository.findById(quizId).get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		quizRepository.deleteById(quizId);
		
	}

	@Override
	public List<Quiz> getQuizzesofcategory(Category cat) {
		// TODO Auto-generated method stub
		return this.quizRepository.findBycategory(cat);
	}

	@Override
	public List<Quiz> getActiveQuizzes() {
		// TODO Auto-generated method stub
		return this.quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizzesofCategory(Category cat) {
		// TODO Auto-generated method stub
		return this.quizRepository.findByCategoryAndActive(cat, true);
	}

}
