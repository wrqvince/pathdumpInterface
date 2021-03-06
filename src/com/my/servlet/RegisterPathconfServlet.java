package com.my.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.util.PathdumpUtils;

/**
 * Servlet implementation class RegisterPathonfServlet
 */
public class RegisterPathconfServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String queryName = request.getParameter("filename");
		String queryPath = getServletContext().getRealPath("/file/pathconf_check.py");
		String queryContent = PathdumpUtils.registerQuery(queryPath, queryName);
		ArrayList<String> list = (ArrayList<String>)request.getSession().getAttribute("queryName");
		if(list == null){
			list = new ArrayList<String>();
			list.add(queryName);
		}else{
			list.add(queryName);
		}
		if(queryContent.equals("[true]")){
			request.getSession().setAttribute("queryName", list);
			request.getSession().setAttribute("pathConfFlag", true);
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
