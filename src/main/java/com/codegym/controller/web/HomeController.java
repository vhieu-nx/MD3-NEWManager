package com.codegym.controller.web;

import com.codegym.model.UserModel;
import com.codegym.service.ICategoryService;
import com.codegym.service.IUserService;
import com.codegym.utils.FormUtil;
import com.codegym.utils.SessionUtil;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;
//"/dang-nhap","/thoat"
@WebServlet(urlPatterns = {"/trang-chu","/dang-nhap","/thoat"})
public class HomeController extends HttpServlet {
	@Inject
	private ICategoryService categoryService;
	@Inject
	private IUserService userService;
//
//
//	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String  action = request.getParameter("action");
		if (action != null && action.equals("login")){
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		}else if (action != null && action.equals("logout")){
			SessionUtil.getInstance().removeValue(request,"USERMODEL");
			response.sendRedirect(request.getContextPath() + "/trang-chu");

		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}

//			request.setAttribute("categories", categoryService.findAll());


	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel model = FormUtil.toModel(UserModel.class, request);
			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(),model.getPassword(),1);
			if (model != null){
				SessionUtil.getInstance().putValue(request,"USERMODEL",model);
				if (model.getRole().getCode().equals("USER")){
					response.sendRedirect(request.getContextPath()+ "/trang-chu");
				}else if (model.getRole().getCode().equals("ADMIN")){
					response.sendRedirect(request.getContextPath()+ "/admin-home");
				}
			}else{
				response.sendRedirect(request.getContextPath()+ "/trang-chu?action=login");
			}
		}
//			model = userService.findByUserNameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
//			if (model != null) {
//				SessionUtil.getInstance().putValue(request, "USERMODEL", model);
//				if (model.getRole().getCode().equals("USER")) {
//					response.sendRedirect(request.getContextPath()+"/trang-chu");
//				} else if (model.getRole().getCode().equals("ADMIN")) {
//					response.sendRedirect(request.getContextPath()+"/admin-home");
//				}
//			} else {
//				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=username_password_invalid&alert=danger");
//			}
//		}
	}
}
