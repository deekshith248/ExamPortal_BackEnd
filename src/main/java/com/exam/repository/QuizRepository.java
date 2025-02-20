package com.exam.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Category;
import com.exam.model.Quiz;



public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	public List<Quiz> findBycategory(Category cat);
	
	public List<Quiz> findByActive(Boolean active);
	
	public List<Quiz> findByCategoryAndActive(Category cat,Boolean active);

}
