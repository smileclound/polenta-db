package com.polenta.db.sorting.impl;

import java.util.List;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.Sorter;

public class InsertionSorter extends Sorter {

	public List<Row> sort(List<Row> list, String criteria) throws PolentaException {
		for (int i = 1; i < list.size(); i++) {
			for (int j = i; j > 0 && (compare(list.get(j), list.get(j - 1), criteria) < 0); j--) {
				exchange(list, j, j -1);
			}
		}
		return list;
	}

}
