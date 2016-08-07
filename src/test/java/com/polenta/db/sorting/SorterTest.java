package com.polenta.db.sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.polenta.db.data.Row;

public class SorterTest {

	class GenericSorter extends Sorter {
		@Override
		public List<Row> sort(List<Row> unsorted, String criteria) {
			return null;
		}
	}
	
	@Test
	public void testCompareEmptyMapsAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		Row map2 = new Row();
		
		int compare = sorter.compare(map1, map2, "NAME");
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareEmptyMapsDesc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		Row map2 = new Row();
		
		int compare = sorter.compare(map1, map2, "NAME DESC");
		assertEquals(0,  compare);
	}
	
	@Test
	public void testCompareAscBothMapsHasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);
		
		Row map2 = new Row();
		map2.set("NAME", null);
		
		int compare = sorter.compare(map1, map2, "NAME");
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareDescBothMapsNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);

		Row map2 = new Row();
		map2.set("NAME", null);

		int compare = sorter.compare(map1, map2, "NAME DESC");
		assertEquals(0,  compare);
	}

	@Test
	public void testCompareAscMap1HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);

		Row map2 = new Row();
		map2.set("NAME", "A");
		
		int compare = sorter.compare(map1, map2, "NAME");
		assertEquals(-1,  compare);
	}

	@Test
	public void testCompareDescMap1HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", null);

		Row map2 = new Row();
		map2.set("NAME", "A");
		
		int compare = sorter.compare(map1, map2, "NAME DESC");
		assertEquals(1,  compare);
	}
	
	@Test
	public void testCompareAscMap2HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", null);

		int compare = sorter.compare(map1, map2, "NAME");
		assertEquals(1,  compare);
	}

	@Test
	public void testCompareDescMap2HasNullName() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", null);

		int compare = sorter.compare(map1, map2, "NAME DESC");
		assertEquals(-1,  compare);
	}
	
	@Test
	public void testCompareAtoZAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", "Z");

		int compare = sorter.compare(map1, map2, "NAME");
		assertTrue(compare < 0);
	}
	
	@Test
	public void testCompareAtoZDesx() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "A");

		Row map2 = new Row();
		map2.set("NAME", "Z");

		int compare = sorter.compare(map1, map2, "NAME DESC");
		assertTrue(compare > 0);
	}

	@Test
	public void testCompareZtoAAsc() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "Z");

		Row map2 = new Row();
		map2.set("NAME", "A");
		
		int compare = sorter.compare(map1, map2, "NAME");
		assertTrue(compare > 0);
	}
	
	@Test
	public void testCompareZtoADesx() throws Exception {
		GenericSorter sorter = new GenericSorter();
		
		Row map1 = new Row();
		map1.set("NAME", "Z");

		Row map2 = new Row();
		map2.set("NAME", "A");

		int compare = sorter.compare(map1, map2, "NAME DESC");
		assertTrue(compare < 0);
	}
}
