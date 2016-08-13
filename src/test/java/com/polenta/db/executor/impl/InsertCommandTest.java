package com.polenta.db.executor.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.polenta.db.executor.impl.InsertExecutor;

public class InsertCommandTest {

	@Test
	public void testExtractFieldNames() throws Exception {
		InsertExecutor command = new InsertExecutor();

		List<String> fields = command.extractFieldNames("INSERT INTO MY_BAG (NAME, SALARY, BIRTH, ZIP) VALUES ('PAULO', 1000.00, 12/30/2000, 01000)");

		assertTrue(fields != null);
		assertEquals(4, fields.size());
		assertEquals("NAME", fields.get(0));
		assertEquals("SALARY", fields.get(1));
		assertEquals("BIRTH", fields.get(2));
		assertEquals("ZIP", fields.get(3));
	}

	@Test
	public void testExtractRawFieldValues() throws Exception {
		InsertExecutor command = new InsertExecutor();

		List<String> fields = command.extractRawFieldValues("INSERT INTO MY_BAG (NAME, SALARY, BIRTH, ZIP) VALUES ('PAULO REZENDE', 1000.00, 2000-01-01, 01000)");

		assertTrue(fields != null);
		assertEquals(4, fields.size());
		assertEquals("'PAULO REZENDE'", fields.get(0));
		assertEquals("1000.00", fields.get(1));
		assertEquals("2000-01-01", fields.get(2));
		assertEquals("01000", fields.get(3));
	}

}
