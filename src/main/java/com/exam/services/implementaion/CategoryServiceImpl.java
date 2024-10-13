package com.exam.services.implementaion;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.Category;
import com.exam.repository.CategoryRepository;
import com.exam.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return  categoryRepository.findAll();
	}

	@Override
	public Category getCategory(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id).get();
	}

	@Override
	public void deleteCategory(Long id) {
		Category category = new Category();
		category.setCategoryId(id);
		categoryRepository.delete(category);
		// TODO Auto-generated method stub

	}

}
