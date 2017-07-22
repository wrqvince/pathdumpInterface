package com.my.util;

import java.util.Properties;

import org.python.util.PythonInterpreter;

public class JythonUtils {
	public static void init(){
		Properties props = new Properties();
		props.put("python.home", "path to the Lib folder");
		props.put("python.console.encoding", "UTF-8");
		props.put("python.security.respectJavaAccessibility", "false");
		props.put("python.import.site", "false");
		Properties preprops = System.getProperties();
		PythonInterpreter.initialize(preprops, props, new String[0]);
	}
}
