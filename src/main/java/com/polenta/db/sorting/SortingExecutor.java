package com.polenta.db.sorting;

import java.util.List;

import com.polenta.db.data.ResultSet;
import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.impl.InsertionSorter;
import com.polenta.db.sorting.impl.SelectionSorter;
import com.polenta.db.sorting.impl.ShellSorter;

public class SortingExecutor {

	public static ResultSet sort(List<Row> unsorted, List<String> criterias) throws PolentaException {
		if (criterias == null || criterias.isEmpty()) {
			throw new PolentaException("ORDER BY requires fields.");
		}
		if (criterias.size() != 1) {
			throw new PolentaException("ORDER BY does not support (yet!) more then a field.");
		}
		Sorter sorter = buildSorter(unsorted);
		
		if (sorter != null) {
			return new ResultSet(sorter.sort(unsorted, criterias));
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
