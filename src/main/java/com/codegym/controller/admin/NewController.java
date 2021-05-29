package com.codegym.controller.admin;

import com.codegym.constant.SystemConstant;
import com.codegym.model.NewModel;
import com.codegym.service.INewService;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewModel model = new NewModel();
		String page  = request.getParameter("page");
		String maxPageItem  = request.getParameter("maxPageItem");
		if (page != null){
			model.setPage(Integer.parseInt(page));
		}else {
			model.setPage(1);
		}
		if (maxPageItem != null){
			model.setMaxPageItem(Integer.parseInt(maxPageItem));
		}
		Integer offset = (model.getPage() - 1 ) * model.getMaxPageItem();
		model.setListResult(newService.findAll(offset,model.getMaxPageItem()));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double)model.getTotalItem()/model.getMaxPageItem()));
		request.setAttribute(SystemConstant.MODEL,model);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/new/list.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
