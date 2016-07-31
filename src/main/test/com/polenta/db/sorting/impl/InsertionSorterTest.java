package com.polenta.db.sorting.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.polenta.db.data.Row;
import com.polenta.db.exception.PolentaException;
import com.polenta.db.sorting.SorterMocker;

public class InsertionSorterTest extends InsertionSorter {
	
	@Test
	public void testSort1() throws PolentaException {
		List<Row> unsortedRows = SorterMocker.mockSetOfNames();
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("name desc");

		List<Row> sorted = sort(unsortedRows, criterias);
		assertEquals(unsortedRows.size(), sorted.size());
		assertEquals("Z", sorted.get(0).get("name")); 
		assertEquals("T", sorted.get(1).get("name")); 
		assertEquals("M", sorted.get(2).get("name")); 
		assertEquals("C", sorted.get(3).get("name")); 
		assertEquals("A", sorted.get(4).get("name")); 
		
	}
	
	@Test
	public void testSort2() throws PolentaException {
		List<Row> unsortedRows = SorterMocker.mockSetOfIDs();
		
		List<String> criterias = new ArrayList<String>();
		criterias.add("id");

		List<Row> sorted = sort(unsortedRows, criterias);
		assertEquals(unsortedRows.size(), sorted.size());
		for (int i = 1; i <= 10; i++) {
			assertEquals(new Integer(i), sorted.get(i - 1).get("id")); 
		}
	}

}
