package com.polenta.db.sorting;

import java.util.List;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.impl.BottomUpMergeSorter;
import com.polenta.db.sorting.impl.InsertionSorter;
import com.polenta.db.sorting.impl.QuickSorter;
import com.polenta.db.sorting.impl.SelectionSorter;
import com.polenta.db.sorting.impl.ShellSorter;
import com.polenta.db.sorting.impl.TopDownMergeSorter;

public class SortingExecutor {

	public static List sort(List unsorted, List<String> criterias) throws PolentaException {
		if (criterias == null || criterias.isEmpty()) {
			throw new PolentaException("ORDER BY requires fields.");
		}
		if (criterias.size() != 1) {
			throw new PolentaException("ORDER BY does not support (yet!) more then a field.");
		}
		Sorter sorter = buildSorter(unsorted);
		
		if (sorter != null) {
			return sorter.sort(unsorted);
		} else {
			throw new PolentaException("ORDER BY clausule could not be executed. No sorter found.");
		}
	}
	
	private static Sorter buildSorter(List unsorted) {
		int size = unsorted.size();
		if (size <= 10) {
			return new SelectionSorter();
		} else if (size <= 50) {
			return new InsertionSorter();
		} else if (size <= 100) {
			return new ShellSorter();
		} else if (size <= 500) {
			return new BottomUpMergeSorter();
		} else if (size <= 1000) {
			return new TopDownMergeSorter();
		} else {
			return new QuickSorter();
		}
		
	}
	
	
}
