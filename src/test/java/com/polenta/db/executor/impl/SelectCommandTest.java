package com.polenta.db.executor.impl;


import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.executor.impl.SelectExecutor;

public class SelectCommandTest {

	@Test
	public void testExtractNullObjectName() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		String name = command.extractObjectName("SELECT NAME");
		assertEquals(null, name);
	}

	@Test
	public void testExtractObjectName() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		String name = command.extractObjectName("SELECT NAME FROM MY_BAG");
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractObjectNameWithWhereClausule() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		String name = command.extractObjectName("SELECT NAME FROM MY_BAG WHERE SALARY > 1000");
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractObjectNameWithOrderByClausule() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		String name = command.extractObjectName("SELECT NAME FROM MY_BAG ORDER BY NAME");
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractObjectNameWithWhereAndOrderByClausule() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		String name = command.extractObjectName("SELECT NAME FROM MY_BAG WHERE SALARY > 1000 AND ZIP = 03000 ORDER BY NAME");
		assertEquals("MY_BAG", name);
	}

	@Test
	public void testExtractSelectFields() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		List<String> fields = command.extractSelectFields("SELECT NAME, SALARY, ZIP FROM MY_BAG");
		assertEquals(3, fields.size());
		assertEquals("NAME", fields.get(0));
		assertEquals("SALARY", fields.get(1));
		assertEquals("ZIP", fields.get(2));
		
	}

	@Test
	public void testExtractOrderByFieldsWhenClausuleDoesntExist() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		List<String> fields = command.extractOrderByFields("SELECT NAME, SALARY, ZIP FROM MY_BAG");
		assertEquals(0, fields.size());
	}

	@Test
	public void testExtractOrderByFieldsWhenClausuleEmpty() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		List<String> fields = command.extractOrderByFields("SELECT NAME, SALARY, ZIP FROM MY_BAG ORDER BY");
		assertEquals(0, fields.size());
	}

	@Test
	public void testExtractOrderByFields() throws PolentaException {
		SelectExecutor command = new SelectExecutor();

		List<String> fields = command.extractOrderByFields("SELECT NAME, SALARY, ZIP FROM MY_BAG ORDER BY SALARY");
		assertEquals(1, fields.size());
		assertEquals("SALARY", fields.get(0));
	}
	
}
