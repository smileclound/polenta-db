package com.polenta.db.command.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.polenta.db.command.impl.CreateCommand;

public class CreateCommandTest {

	@Test
	public void testCreateBagExtractDefinitions() throws Exception {
		CreateCommand command = new CreateCommand();
		command.setStatement("CREATE BAG (NAME STRING, BIRTH DATE, ZIP INTEGER, SALARY DOUBLE)");
		
		Map<String, String> map = command.extractObjectDefinitions();
		
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
