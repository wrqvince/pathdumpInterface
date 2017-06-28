package com.my.test;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.junit.Test;

public class PathdumpTest {
	@Test
	public void testpath(){
		TreeMap<Integer,Integer> map = new TreeMap<Integer,Integer>();
		map.put(1, 2);
		map.put(2, 4);
		map.put(3, 6);
		map.put(4, 8);
		Iterator<Entry<Integer, Integer>> iterator = map.entrySet().iterator();
		int k;
		int value;
		while (iterator.hasNext()){
			k = iterator.next().getKey();
			value = iterator.next().getValue();
			System.out.println(k+" "+value);
		}
	}
	
	
}
