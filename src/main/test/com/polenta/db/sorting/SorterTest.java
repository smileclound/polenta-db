package com.polenta.db.sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SorterTest {

	class GenericSorter extends Sorter {
		@Override
		public List<Map<String, Object>> sort(List<Map<String, Object>> unsorted, List<String> criterias) {
			return null;
		}
	}
	
	@Test
	public void testCompareEmptyMapsAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareEmptyMapsDesc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}
	
	@Test
	public void testCompareAscBothMapsHasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", null);
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", null);
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareDescBothMapsNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", null);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", null);

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareAscMap1HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", null);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", "A");
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(-1,  compare);
	}

	@Test
	public void testCompareDescMap1HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", null);

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", "A");
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(1,  compare);
	}
	
	@Test
	public void testCompareAscMap2HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", "A");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", null);

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(1,  compare);
	}

	@Test
	public void testCompareDescMap2HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", "A");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", null);

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(-1,  compare);
	}
	
	@Test
	public void testCompareAtoZAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", "A");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", "Z");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare < 0);
	}
	
	@Test
	public void testCompareAtoZDesx() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", "A");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", "Z");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare > 0);
	}

	@Test
	public void testCompareZtoAAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", "Z");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", "A");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare > 0);
	}
	
	@Test
	public void testCompareZtoADesx() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("NAME", "Z");

		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("NAME", "A");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare < 0);
	}
}
