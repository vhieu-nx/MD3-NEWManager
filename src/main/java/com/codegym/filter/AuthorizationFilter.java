package com.codegym.filter;

import com.codegym.constant.SystemConstant;
import com.codegym.model.UserModel;
import com.codegym.utils.SessionUtil;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationFilter implements Filter {
    private  ServletContext context;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.context = filterConfig.getServletContext();

        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.startsWith("/admin")){
            UserModel userModel = (UserModel) SessionUtil.getInstance().getValue(request,"USERMODEL");
            if (userModel!= null){
                if (userModel.getRole().getCode().equals(SystemConstant.ADMIN)){
                    chain.doFilter(servletRequest,servletResponse);
                }else if (userModel.getRole().getCode().equals(SystemConstant.USER)){
                    response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_permission&alert=danger");
                }

            }else {
                response.sendRedirect(request.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
            }

        }else {
            chain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
