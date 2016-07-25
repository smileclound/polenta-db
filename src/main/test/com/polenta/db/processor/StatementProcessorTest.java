package com.polenta.db.processor;

import java.util.Map;

import org.junit.Test;

import com.polenta.db.exception.PolentaException;

import static org.junit.Assert.*;

public class StatementProcessorTest {

	@Test
	public void testCreateBagExtractDefinitions() throws PolentaException {
		
		StatementProcessor sp = new StatementProcessor("CREATE BAG (NAME STRING, BIRTH DATE, ZIP INTEGER, SALARY DOUBLE)");
		
		Map<String, String> map = sp.extractNewObjectDefinitions();
		
		assertEquals(4, map.size());
		
		assertTrue(map.containsKey("NAME"));
		assertTrue(map.containsKey("BIRTH"));
		assertTrue(map.containsKey("ZIP"));
		assertTrue(map.containsKey("SALARY"));
		
		assertEquals("STRING", map.get("NAME"));
		assertEquals("DATE", map.get("BIRTH"));
		assertEquals("INTEGER", map.get("ZIP"));
		assertEquals("DOUBLE", map.get("SALARY"));
	}
	
}
