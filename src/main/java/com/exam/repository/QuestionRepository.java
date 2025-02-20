package com.exam.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Question;
import com.exam.model.Quiz;

public interface QuestionRepository extends JpaRepository<Question,Long> {

	public Set<Question> findByQuiz(Quiz quiz);

}
