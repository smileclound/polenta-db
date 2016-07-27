package com.polenta.db.sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.polenta.db.data.Row;

public class SorterTest {

	class GenericSorter extends Sorter {
		@Override
		public List<Row> sort(List<Row> unsorted, List<String> criterias) {
			return null;
		}
	}
	
	@Test
	public void testCompareEmptyMapsAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		Row map2 = new Row();
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareEmptyMapsDesc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		Row map2 = new Row();
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}
	
	@Test
	public void testCompareAscBothMapsHasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);
		
		Row map2 = new Row();
		map2.set("NAME", null);
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareDescBothMapsNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);

		Row map2 = new Row();
		map2.set("NAME", null);

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareAscMap1HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);

		Row map2 = new Row();
		map2.set("NAME", "A");
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(-1,  compare);
	}

	@Test
	public void testCompareDescMap1HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);

		Row map2 = new Row();
		map2.set("NAME", "A");
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(1,  compare);
	}
	
	@Test
	public void testCompareAscMap2HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", null);

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(1,  compare);
	}

	@Test
	public void testCompareDescMap2HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", null);

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertEquals(-1,  compare);
	}
	
	@Test
	public void testCompareAtoZAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", "Z");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare < 0);
	}
	
	@Test
	public void testCompareAtoZDesx() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", "Z");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare > 0);
	}

	@Test
	public void testCompareZtoAAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "Z");

		Row map2 = new Row();
		map2.set("NAME", "A");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare > 0);
	}
	
	@Test
	public void testCompareZtoADesx() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "Z");

		Row map2 = new Row();
		map2.set("NAME", "A");

		List<String> criterias = new ArrayList<String>();
		criterias.add("NAME DESC");
		
		int compare = sorter.compare(map1, map2, criterias);
		assertTrue(compare < 0);
	}
}
