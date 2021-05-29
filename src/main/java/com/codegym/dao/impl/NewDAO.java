package com.codegym.dao.impl;

import com.codegym.dao.INewDAO;
import com.codegym.mapper.NewMapper;
import com.codegym.model.NewModel;

import java.util.List;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO news (title, content,");
		sql.append(" thumbnail, shortdescription, categoryid, createddate, createdby)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), 
				newModel.getThumbnail(), newModel.getShortDescription(), newModel.getCategoryId(),
				newModel.getCreatedDate(), newModel.getCreatedBy());
	}

	@Override
	public List<NewModel> findAll(Integer offset, Integer limit) {
		String sql = "SELECT * FROM news limit ?,?";
		return query(sql, new NewMapper(),offset,limit);
	}

	@Override
	public int getTotalItem() {
		String sql = "select count(*) from news";
		return count(sql);
	}

//	@Override
//	public NewModel findOne(Long id) {
//		String sql = "SELECT * FROM news WHERE id = ?";
//		List<NewModel> news = query(sql, new NewMapper(), id);
//		return news.isEmpty() ? null : news.get(0);
//	}
//
//	@Override
//	public void update(NewModel updateNew) {
//		StringBuilder sql = new StringBuilder("UPDATE news SET title = ?, thumbnail = ?,");
//		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
//		sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
//		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
//				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(),
//				updateNew.getCreatedBy(), updateNew.getModifiedDate(),
//				updateNew.getModifiedBy(), updateNew.getId());
//	}
//
//	@Override
//	public void delete(long id) {
//		String sql = "DELETE FROM news WHERE id = ?";
//		update(sql, id);
//	}
//
//	@Override
//	public List<NewModel> findAll(Pageble pageble) {
//		StringBuilder sql = new StringBuilder("SELECT * FROM news");
//		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
//			sql.append(" ORDER BY "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy()+"");
//		}
//		if (pageble.getOffset() != null && pageble.getLimit() != null) {
//			sql.append(" LIMIT "+pageble.getOffset()+", "+pageble.getLimit()+"");
//		}
//		return query(sql.toString(), new NewMapper());
//	}
//
//	@Override
//	public int getTotalItem() {
//		String sql = "SELECT count(*) FROM news";
//		return count(sql);
//	}
}
