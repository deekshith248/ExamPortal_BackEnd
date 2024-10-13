package com.exam.services;

import java.util.List;
import java.util.Set;

import com.exam.model.Category;
import com.exam.model.Quiz;


public interface QuizService {
	public Quiz addQuiz(Quiz quiz);
	public Quiz updateQuiz(Quiz quiz);
	public List<Quiz> getQuizzes();
	public Quiz getQuiz(Long quizId);
	public void deleteQuiz(Long quizId);
	public List<Quiz> getQuizzesofcategory(Category cat);
	public List<Quiz> getActiveQuizzes();
	public List<Quiz> getActiveQuizzesofCategory(Category cat);
}
