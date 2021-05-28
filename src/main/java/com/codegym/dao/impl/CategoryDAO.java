package com.codegym.dao.impl;

import com.codegym.dao.ICategoryDAO;
import com.codegym.mapper.CategoryMapper;
import com.codegym.model.CategoryModel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
	@Override
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jspservletjdbc";
			String username = "root";
			String password = "123456";
			return DriverManager.getConnection(url,username,password);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}


	@Override
	public List<CategoryModel> findAll() {
		return null;
	}

}
