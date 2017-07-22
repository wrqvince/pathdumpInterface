package com.my.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.util.PathdumpUtils;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// register query.py
		String queryName = request.getParameter("querySelect");
		String queryPath = getServletContext().getRealPath("/file/"+queryName);
		String queryContent = PathdumpUtils.registerQuery(queryPath, queryName);
		ArrayList<String> list = (ArrayList<String>) request.getSession().getAttribute("queryName");
		if (list == null) {
			list = new ArrayList<String>();
			list.add(queryName);
		} else {
			list.add(queryName);
		}
		
		// register query_agg.py, could be null
		String queryAggName = request.getParameter("aggSelect");
		if(queryAggName.equals("null")){
			if(queryContent.equals("[true]")){
				request.getSession().setAttribute("queryName", list);
				request.getSession().setAttribute("pathConfFlag", true);
			}
		} else {
			String queryAggPath = getServletContext().getRealPath("/file/"+queryAggName);
			String queryAggContent = PathdumpUtils.registerQuery(queryAggPath, queryAggName);
			if (queryContent.equals("[true]") && queryAggContent.equals("[true]")) {
				request.getSession().setAttribute("queryName", list);
				request.getSession().setAttribute("topkFlag", true);
			}
		}
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
