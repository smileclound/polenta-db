package com.polenta.db.command.impl;


import static org.junit.Assert.assertEquals;

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

}
