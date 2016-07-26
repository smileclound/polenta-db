package com.polenta.db.sorting;

import java.util.List;
import java.util.Map;

import com.polenta.db.exception.PolentaException;

public abstract class Sorter {
	
	public abstract List<Map<String, Object>> sort(List<Map<String, Object>> unsorted, List<String> criterias);
	
	protected int compare(Map<String, Object> map1, Map<String, Object> map2, List<String> criterias) throws PolentaException {
		//for now only one field is supported
		String criteria = criterias.get(0);
		String field = criteria.split(" ")[0];
		String direction;
		try {
			direction = criteria.split(" ")[0];
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
			Comparable comparable1 = (Comparable)object1;
			Comparable comparable2 = (Comparable)object2;
			return comparable1.compareTo(comparable2);
		} catch (Exception e) {
			throw new PolentaException("Objects are not comparable.");
		}
		
	}
	
}
