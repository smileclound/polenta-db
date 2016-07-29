package com.polenta.db.sorting;

import java.util.List;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;

public abstract class Sorter {
	
	public abstract List<Row> sort(List<Row> unsorted, List<String> criterias) throws PolentaException;
	
	protected int compare(Row map1, Row map2, List<String> criterias) throws PolentaException {
		//for now only one field is supported
		String criteria = criterias.get(0);
		String field = criteria.split(" ")[0];
		String direction;
		try {
			direction = criteria.split(" ")[1];
		} catch (Exception e) {
			direction = "ASC";
		}
		int compare = compareObjects(map1.get(field), map2.get(field));
		if (direction.equals("DESC")) {
			compare = compare * -1;
		}
		return compare;
	}
	
	public int compareObjects(Object object1, Object object2) throws PolentaException {
		if (object1 == null && object2 == null) {
			return 0;
		}
		if (object1 == object2) {
			return 0;
		}
		if (object1 != null && object2 == null) {
			return 1;
		}
		if (object1 == null && object2 != null) {
			return -1;
		}
		if (!object1.getClass().isAssignableFrom(object2.getClass())) {
			throw new PolentaException("Objects of different types cannot be compared.");
		}
		try {
			Comparable<Object> comparable1 = (Comparable<Object>)object1;
			Comparable<Object> comparable2 = (Comparable<Object>)object2;
			return comparable1.compareTo(comparable2);
		} catch (Exception e) {
			throw new PolentaException("Objects are not comparable.");
		}
		
	}
	
	protected void exchange(List<Row> list, int a, int b) {
		Row mapA = list.get(a);
		Row mapB = list.get(b);
		list.set(a, mapB);
		list.set(b, mapA);
	}
	
}
