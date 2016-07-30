package com.polenta.db.sorting.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;

public class SelectionSorterTest extends SelectionSorter {
	
	@Test
	public void testSort() throws PolentaException {
		List<Row> unsortedRows = new ArrayList<Row>();
		
		Row row1 = new Row();
		row1.set("name", "Z");
		unsortedRows.add(row1);
		
		Row row2 = new Row();
		row2.set("name", "A");
		unsortedRows.add(row2);

		Row row3 = new Row();
		row3.set("name", "C");
		unsortedRows.add(row3);

		Row row4 = new Row();
		row4.set("name", "T");
		unsortedRows.add(row4);

		Row row5 = new Row();
		row5.set("name", "M");
		unsortedRows.add(row5);
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("name");

		List<Row> sorted = sort(unsortedRows, criterias);
		assertEquals(unsortedRows.size(), sorted.size());
		assertEquals("A", sorted.get(0).get("name")); 
		assertEquals("C", sorted.get(1).get("name")); 
		assertEquals("M", sorted.get(2).get("name")); 
		assertEquals("T", sorted.get(3).get("name")); 
		assertEquals("Z", sorted.get(4).get("name")); 
		
	}
	

}
