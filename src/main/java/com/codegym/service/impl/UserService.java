package com.codegym.service.impl;

import com.codegym.dao.IUserDAO;
import com.codegym.dao.impl.UserDAO;
import com.codegym.model.UserModel;
import com.codegym.service.IUserService;

public class UserService implements IUserService {

	private IUserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		return userDAO.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
