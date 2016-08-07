package com.polenta.db.sorting.impl;

import java.util.List;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.Sorter;

public class TopDownMergeSorter extends Sorter {

	private Row[] aux;
	
	public List<Row> sort(List<Row> list, String criteria) throws PolentaException {
		this.aux = new Row[list.size()];
		
		sort(list, criteria, 0, list.size() - 1);
		
		return list;
	}

	protected void sort(List<Row> list, String criteria, int lower, int higher) throws PolentaException {
		if (higher <= lower) {
			return;
		}
		int middle = lower + (higher - lower) / 2;
		sort(list, criteria, lower, middle);
		sort(list, criteria, middle + 1, higher);
		merge(list, criteria, lower, middle, higher);
	}

	protected void merge(List<Row> list, String criteria, int lower, int middle, int higher) throws PolentaException {
		int i = lower;
		int j = middle + 1;
		
		for (int k = lower; k <= higher; k++) {
			aux[k] = list.get(k);
		}
		
		for (int k = lower; k <= higher; k++) {
			if (i > middle) {
				list.set(k, aux[j++]);
			} else if (j > higher) {
				list.set(k, aux[i++]);
			} else if (compare(aux[j], aux[i], criteria) < 0) {
				list.set(k, aux[j++]);
			} else {
				list.set(k, aux[i++]);
			}
		}
	}

}
