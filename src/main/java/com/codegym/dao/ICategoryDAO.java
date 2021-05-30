package com.codegym.dao;

import com.codegym.model.CategoryModel;

import java.util.List;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
	List<CategoryModel> findAll();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
}
