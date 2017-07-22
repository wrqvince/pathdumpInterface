package com.my.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.python.util.PythonInterpreter;

import com.my.util.PathdumpUtils;

/**
 * Servlet implementation class UninstallTopkServlet
 */
public class UninstallTopkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, Object> tree = PathdumpUtils.getAggTree();
		HashMap<String, Object> query = new HashMap<String, Object>();
		query.put("name", "topk_query.py");
		String[] argv = new String[]{""};
		query.put("argv", argv);
		String queryResult = PathdumpUtils.uninstallQuery(tree,query);
		HashMap<String, Object> query2 = new HashMap<String, Object>();
		query2.put("name", "topk_query_agg.py");
		query2.put("argv", argv);
		String queryResult2 = PathdumpUtils.uninstallQuery(tree,query2);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
