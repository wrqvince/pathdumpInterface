package com.my.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.util.PathdumpUtils;

import net.dongliu.requests.RawResponse;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] filenames = request.getParameter("filename").split("&");
		// register query.py
		String queryName = filenames[0];
		String queryPath = getServletContext().getRealPath("/file/topk_query.py");
		String queryContent = PathdumpUtils.registerQuery(queryPath, queryName);
		// register query_agg.py
		String queryAggName = filenames[1];
		String queryAggPath = getServletContext().getRealPath("/file/topk_query_agg.py");
		String queryAggContent = PathdumpUtils.registerQuery(queryAggPath, queryAggName);
		System.out.println("Register topk_query.py ----> " + queryContent + "\nRegister topk_query_agg.py ----> "
				+ queryAggContent);
		request.setAttribute("queryContent", queryContent);
		request.setAttribute("queryAggContent", queryAggContent);
		
		if ((queryContent != null) && (queryAggContent != null)) {
			request.getRequestDispatcher("/pages/registerSuccess.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/pages/registerFail.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
