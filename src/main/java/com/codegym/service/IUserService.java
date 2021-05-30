package com.codegym.service;

import com.codegym.model.UserModel;

public interface IUserService {
    UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
