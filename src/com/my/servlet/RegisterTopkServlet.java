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
public class RegisterTopkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// register query.py
		String queryName = request.getParameter("filename1");
		String queryPath = getServletContext().getRealPath("/file/topk_query.py");
		String queryContent = PathdumpUtils.registerQuery(queryPath, queryName);
		// register query_agg.py
		String queryAggName = request.getParameter("filename2");
		String queryAggPath = getServletContext().getRealPath("/file/topk_query_agg.py");
		String queryAggContent = PathdumpUtils.registerQuery(queryAggPath, queryAggName);
		ArrayList<String> list = (ArrayList<String>)request.getSession().getAttribute("queryName");
		if(list == null){
			list = new ArrayList<String>();
			list.add(queryName);
		}else{
			list.add(queryName);
		}
		if(queryContent.equals("[true]") && queryAggContent.equals("[true]")){
			request.getSession().setAttribute("queryName", list);
			request.getSession().setAttribute("topkFlag", true);
		}
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
