package com.codegym.controller.admin.api;

import com.codegym.model.NewModel;
import com.codegym.model.UserModel;
import com.codegym.service.INewService;
import com.codegym.utils.HttpUtil;
import com.codegym.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-new"})
public class NewAPI extends HttpServlet {
    @Inject
    private INewService newService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
        newModel.setCreatedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
        newModel = newService.save(newModel);
        objectMapper.writeValue(resp.getOutputStream(), newModel);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
        newModel.setModifiedBy(((UserModel) SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
        newModel = newService.update(newModel);
        objectMapper.writeValue(resp.getOutputStream(),newModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        NewModel newModel = HttpUtil.of(req.getReader()).toModel(NewModel.class);
        newService.delete(newModel.getIds());
        objectMapper.writeValue(resp.getOutputStream(),"{}");
    }
}
