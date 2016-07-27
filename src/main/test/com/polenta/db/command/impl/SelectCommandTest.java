package com.polenta.db.command.impl;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.polenta.db.exception.PolentaException;

public class SelectCommandTest {

	@Test
	public void testExtractNullObjectName() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME");
		String name = command.extractObjectName();
		assertEquals(null, name);
	}

	@Test
	public void testExtractObjectName() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME FROM MY_BAG");
		String name = command.extractObjectName();
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractObjectNameWithWhereClausule() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME FROM MY_BAG WHERE SALARY > 1000");
		String name = command.extractObjectName();
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractObjectNameWithOrderByClausule() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME FROM MY_BAG ORDER BY NAME");
		String name = command.extractObjectName();
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractObjectNameWithWhereAndOrderByClausule() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME FROM MY_BAG WHERE SALARY > 1000 AND ZIP = 03000 ORDER BY NAME");
		String name = command.extractObjectName();
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractSelectFields() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME, SALARY, ZIP FROM MY_BAG");
		List<String> fields = command.extractSelectFields();
		assertEquals(3, fields.size());
		assertEquals("NAME", fields.get(0));
		assertEquals("SALARY", fields.get(1));
		assertEquals("ZIP", fields.get(2));
		
	}

	@Test
	public void testExtractOrderByFieldsWhenClausuleDoesntExist() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME, SALARY, ZIP FROM MY_BAG");
		List<String> fields = command.extractOrderByFields();
		assertEquals(0, fields.size());
	}

	@Test
	public void testExtractOrderByFieldsWhenClausuleEmpty() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME, SALARY, ZIP FROM MY_BAG ORDER BY");
		List<String> fields = command.extractOrderByFields();
		assertEquals(0, fields.size());
	}

	@Test
	public void testExtractOrderByFields() throws PolentaException {
		SelectCommand command = new SelectCommand();
		command.setStatement("SELECT NAME, SALARY, ZIP FROM MY_BAG ORDER BY SALARY");
		List<String> fields = command.extractOrderByFields();
		assertEquals(1, fields.size());
		assertEquals("SALARY", fields.get(0));
	}

	@Test
	public void testFormatResultSetToTransport() {
		SelectCommand command = new SelectCommand();
		
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("NAME", "PEDRO");
		map1.put("AGE", null);
		
		Map<String, Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("NAME", "TIAGO");
		map2.put("AGE", new Integer(38));
		
		List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
		rows.add(map1);
		rows.add(map2);
		
		String formatted = command.formatResultSetToTransport(rows);
		
		assertEquals("|NAME:'PEDRO',AGE:NULL|NAME:'TIAGO',AGE:38|", formatted);
		
	}
	
}
