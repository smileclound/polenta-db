package com.polenta.db.command.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.polenta.db.command.impl.CreateCommand;
import com.polenta.db.data.DataType;

public class CreateCommandTest {

	@Test
	public void testCreateBagExtractDefinitions() throws Exception {
		CreateCommand command = new CreateCommand();
		command.setStatement("CREATE BAG (NAME STRING, BIRTH DATE, ZIP INTEGER, SALARY DOUBLE)");
		
		Map<String, DataType> map = command.extractObjectDefinitions();
		
		assertEquals(4, map.size());
		
		assertTrue(map.containsKey("NAME"));
		assertTrue(map.containsKey("BIRTH"));
		assertTrue(map.containsKey("ZIP"));
		assertTrue(map.containsKey("SALARY"));
		
		assertEquals(DataType.STRING, map.get("NAME"));
		assertEquals(DataType.DATE, map.get("BIRTH"));
		assertEquals(DataType.INTEGER, map.get("ZIP"));
		assertEquals(DataType.DOUBLE, map.get("SALARY"));
	}
	
}
