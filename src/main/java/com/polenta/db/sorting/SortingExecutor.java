package com.polenta.db.sorting;

import java.util.List;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.impl.InsertionSorter;
import com.polenta.db.sorting.impl.SelectionSorter;
import com.polenta.db.sorting.impl.ShellSorter;

public class SortingExecutor {

	public static List<Row> sort(List<Row> unsorted, String criteria) throws PolentaException {
		if (criteria == null || criteria.equals("")) {
			throw new PolentaException("ORDER BY requires fields.");
		}

		Sorter sorter = buildSorter(unsorted);
		
		if (sorter != null) {
			return sorter.sort(unsorted, criteria);
		} else {
			throw new PolentaException("ORDER BY clausule could not be executed. No sorter found.");
		}
	}
	
	private static Sorter buildSorter(List<Row> unsorted) {
		int size = unsorted.size();
		if (size <= 10) {
			return new SelectionSorter();
		} else if (size <= 50) {
			return new InsertionSorter();
		} else if (size <= 100) {
			return new ShellSorter();
		} else if (size <= 500) {
			return new ShellSorter();
			//return new BottomUpMergeSorter();
		} else if (size <= 1000) {
			return new ShellSorter();
			//return new TopDownMergeSorter();
		} else {
			return new ShellSorter();
			//return new QuickSorter();
		}
		
	}
	
	
}
