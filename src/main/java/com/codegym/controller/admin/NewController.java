package com.codegym.controller.admin;

import com.codegym.constant.SystemConstant;
import com.codegym.model.NewModel;
import com.codegym.paging.PageRequest;
import com.codegym.paging.Pageble;
import com.codegym.service.ICategoryService;
import com.codegym.service.INewService;
import com.codegym.sort.Sorter;
import com.codegym.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-new"})
public class NewController extends HttpServlet {
	@Inject
	private INewService newService;
	@Inject
	private ICategoryService categoryService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//refactor dung FormUtils
		NewModel model = FormUtil.toModel(NewModel.class, request);
		String views = "";
		if (model.getType().equals(SystemConstant.LIST)){
				//code chua refactor
//				new NewModel();
//		String page  = request.getParameter("page");
//		String maxPageItem  = request.getParameter("maxPageItem");
//		if (page != null){
//			model.setPage(Integer.parseInt(page));
//		}else {
//			model.setPage(1);
//		}
//		if (maxPageItem != null){
//			model.setMaxPageItem(Integer.parseInt(maxPageItem));
//		}
		Pageble pageble = new PageRequest(model.getPage(), model.getMaxPageItem(),
				new Sorter(model.getSortName(), model.getSortBy()));
		model.setListResult(newService.findAll(pageble));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
//		request.setAttribute(SystemConstant.MODEL,model);
		views = "/views/admin/new/list.jsp";

		}else if (model.getType().equals(SystemConstant.EDIT)){
			if (model.getId() !=null){
				model = newService.findOne(model.getId());
			}else {

			}
			request.setAttribute("categories",categoryService.findAll());
			views = "/views/admin/new/edit.jsp";

		}
		request.setAttribute(SystemConstant.MODEL,model);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(views);
		requestDispatcher.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
