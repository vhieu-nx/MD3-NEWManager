package com.codegym.dao;

import com.codegym.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
