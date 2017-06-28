package com.my.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.dongliu.requests.RawResponse;
import net.dongliu.requests.Requests;
import net.sf.json.JSONObject;

public class PathdumpUtils {
	private static String url = "http://192.168.137.138:5000/pathdump";
	/**
	 * Read the content of a file, according to the path of this file
	 * @param filePath
	 * @return
	 */
	public static String readFile(String filePath){
		FileReader reader = null;
		BufferedReader br = null;
		StringBuffer sb = null;
		String str = null;
		try {
			// read file content from file
			sb = new StringBuffer("");

			reader = new FileReader(filePath);
			br = new BufferedReader(reader);

			while ((str = br.readLine()) != null) {
				sb.append(str + "\n");
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
	/**
	 * Http get method
	 * @param api
	 * @param filename
	 * @param data
	 * @return
	 */
	public static RawResponse sendGetRequest(HashMap<String, Object> data) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		//change map to json object
		Charset cs = Charset.forName("gbk");
		JSONObject jsonData = JSONObject.fromObject(data); //change HashMap to Json string.
		
		return Requests.get(url).headers(headers).requestCharset(cs)// request encode
				.body(jsonData.toString())// content of query.py
				.send().withCharset(cs);// receive encode
	}
	
	/**
	 * Http post method
	 * @param api
	 * @param filename
	 * @param data
	 * @return
	 */
	public static RawResponse sendPostRequest(HashMap<String, String> data) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		//change map to json object
		Charset cs = Charset.forName("gbk");
		JSONObject jsonData = JSONObject.fromObject(data); //change HashMap to Json string.
		return Requests.post(url).headers(headers).requestCharset(cs)// request encode
				.body(jsonData.toString())// content of query.py
				.send().withCharset(cs);// receive encode
		
	}
	
	/**
	 * Get aggregation tree
	 * @return
	 */
	public static HashMap<String,Object> getAggTree(){
		HashMap<String, Object> queryGetAggTree = new HashMap<String, Object>();
		String[] groupnodes = {"controller"};
		queryGetAggTree.put("api", "getAggTree");
		queryGetAggTree.put("groupnodes", groupnodes);
		
		RawResponse result = PathdumpUtils.sendGetRequest(queryGetAggTree);
		String content = result.readToText();
		content = content.substring(1, content.length()-1);
		//change jsonString to hashmap object
		HashMap<String,Object> map = (HashMap<String, Object>) JSONObject.toBean(JSONObject.fromObject(content),HashMap.class);
		if(result.getStatusCode()==200){
			return map;
		} else {
			return null;
		}
	}
	
	public static String registerQuery(String queryPath,String queryName){
		HashMap<String, Object> query = new HashMap<String, Object>();
		String queryContent = PathdumpUtils.readFile(queryPath);
		query.put("api", "registerQuery");
		query.put("data", queryContent);
		query.put("name", queryName);
		
		RawResponse result = PathdumpUtils.sendGetRequest(query);
		String content = result.readToText();
		if(result.getStatusCode()==200){
			return content;
		} else {
			return null;
		}
	}
	
	public static ArrayList<Object[]> executeQuery(HashMap<String,Object> tree, HashMap<String,Object> query, HashMap<String,Object> aggCode){
		HashMap<String, Object> req = new HashMap<String, Object>();
		req.put("api", "execQuery");
		req.put("tree", tree);
		req.put("query", query);
		req.put("aggcode", aggCode);
		RawResponse result = PathdumpUtils.sendGetRequest(req);
		String content = result.readToText();
		content = content.substring(2, content.length()-2);
		String[] split = content.split("\\], \\[");
		ArrayList<Object[]> list = new ArrayList<Object[]>();
		Map<String, Class> classMap = new HashMap<String, Class>();  
		classMap.put("flowid", HashMap.class);
		for(String s:split){
			Object[] obj = new Object[2];
			String[] text = s.split(",", 2);	
			HashMap<String, Object> map = (HashMap<String, Object>) JSONObject.toBean(JSONObject.fromObject(text[1]),HashMap.class,classMap);
			obj[0] = text[0];
			obj[1] = map;
			list.add(obj);
		}
		if(result.getStatusCode()==200){
			return list;
		} else {
			return null;
		}
	}
	
//	public static checkSource(){
//		
//	}
	
}
