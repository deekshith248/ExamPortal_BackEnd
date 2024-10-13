package com.exam.services;

import java.util.List;
import java.util.Set;

import com.exam.model.Category;

public interface CategoryService {

	public Category addCategory(Category category);

	public Category updateCategory(Category category);

	public List<Category> getAllCategories();

	public Category getCategory(Long id);

	public void deleteCategory(Long id);

}
