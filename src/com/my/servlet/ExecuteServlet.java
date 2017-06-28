package com.my.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.util.PathdumpUtils;

/**
 * Servlet implementation class ExecuteServlet
 */
public class ExecuteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num_of_query = Integer.valueOf(request.getParameter("num_of_query"));	//topk query, user input
		HashMap<String, Object> tree = PathdumpUtils.getAggTree();
		HashMap<String, Object> query = new HashMap<String, Object>();
		String[] str = { "*", "*" };				//linkID and timeRange
		Object[] obj = { num_of_query, str, str };
		query.put("name", "topk_query.py");
		query.put("argv", obj);
		HashMap<String, Object> aggCode = new HashMap<String, Object>();
		int[] topk = { Integer.valueOf(num_of_query) };
		aggCode.put("name", "topk_query_agg.py");
		aggCode.put("argv", topk);

		ArrayList<Object[]> result = PathdumpUtils.executeQuery(tree, query, aggCode);		//execute query, return result
		TreeMap<Integer, HashMap<String, Object>> output = processData(result);			//turn the result to a treemap, so the sorted data can be displayed
		request.setAttribute("output", output);
		request.setAttribute("num_of_query", num_of_query);
		request.getRequestDispatcher("/pages/executeTopkSuccess.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * process output data to a treeMap, which is able to display in website
	 */
	public TreeMap<Integer, HashMap<String, Object>> processData(ArrayList<Object[]> result){
		TreeMap<Integer, HashMap<String, Object>> output = new TreeMap<Integer, HashMap<String, Object>>(
				new Comparator<Integer>() {
					public int compare(Integer a, Integer b) {
						return b - a;
					}
				});
		
		for (int i = 0; i < result.size(); i++) {
			HashMap<String, Object> outMap = new HashMap<String, Object>();
			Object[] content = result.get(i);
			Integer bytes = Integer.valueOf((String)content[0]);
			HashMap<String, Object> map = (HashMap<String, Object>) content[1];
			HashMap<String, Object> flowid = (HashMap<String, Object>)map.get("flowid");
			outMap.put("flowid", flowid);
			outMap.put("path", map.get("path"));
			output.put(bytes, outMap);
		}
		
		return output;
	}
	
}
