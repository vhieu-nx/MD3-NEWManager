package com.codegym.dao;

import com.codegym.model.NewModel;

import java.util.List;

public interface INewDAO extends GenericDAO<NewModel> {
//	NewModel findOne(Long id);
	List<NewModel> findByCategoryId(Long categoryId);
	Long save(NewModel newModel);
	List<NewModel> findAll();
//	void update(NewModel updateNew);
//	void delete(long id);
//
//	int getTotalItem();
}
