package com.polenta.db.sorting.impl;

import java.util.List;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.Sorter;

public class ShellSorter extends Sorter {

	public List<Row> sort(List<Row> list, List<String> criterias) throws PolentaException {
		int h = 1;
		while (h < (list.size() / 3)) { 
			h = (h * 3) + 1;
		}
		while (h >= 1) {
			for (int i = h; i < list.size(); i++) {
				for (int j = i; j >= h && (compare(list.get(j), list.get(j - h), criterias) < 0); j = j - h) {
					exchange(list, j, j -h);
				}
			}
			h = h / 3;
		}
		
		
		return list;
	}

}
