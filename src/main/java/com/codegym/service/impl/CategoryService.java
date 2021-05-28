package com.codegym.service.impl;

import com.codegym.dao.ICategoryDAO;
import com.codegym.model.CategoryModel;
import com.codegym.service.ICategoryService;

import javax.inject.Inject;
import java.util.List;

public class CategoryService implements ICategoryService {
	//public categoryservice(){categoryDao = new categoryDao()}

	@Inject
	private ICategoryDAO categoryDao;

	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}
}
