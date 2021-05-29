package com.codegym.service;

import com.codegym.model.NewModel;
import java.util.List;

public interface INewService {
	List<NewModel> findByCategoryId(Long categoryId);
	NewModel save(NewModel newModel);

//	NewModel update(NewModel updateNew);
//	void delete(long[] ids);
	List<NewModel> findAll(Integer offset, Integer limit);
	int getTotalItem();
//	NewModel findOne(long id);

}
