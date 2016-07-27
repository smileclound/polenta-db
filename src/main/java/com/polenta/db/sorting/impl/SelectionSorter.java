package com.polenta.db.sorting.impl;

import java.util.List;
import java.util.Map;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.Sorter;

public class SelectionSorter extends Sorter {

	public List<Map<String, Object>> sort(List<Map<String, Object>> list, List<String> criterias) throws PolentaException {
		for (int i = 0; i < list.size(); i++) {
			int min = i;
			for (int j = i + 1; j < list.size(); j++) {
				if (compare(list.get(j), list.get(min), criterias) < 0) {
					min = j;
				}
			}
			exchange(list, i, min);
		}
		return list;
	}
	
	

}
