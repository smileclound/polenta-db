package com.polenta.db.sorting.impl;

import java.util.List;
import java.util.Map;

import com.polenta.db.sorting.Sorter;

public class ShellSorter extends Sorter {

	public List<Map<String, Object>> sort(List<Map<String, Object>> unsorted, List<String> criterias) {
		return unsorted;
	}

}
