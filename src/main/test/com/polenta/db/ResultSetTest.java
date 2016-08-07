package com.polenta.db;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.polenta.db.data.ResultSet;
import com.polenta.db.data.Row;

public class ResultSetTest {
	
	@Test
	public void testFormatResultSetToTransport() {
		Row map1 = new Row();
		map1.set("NAME", "PETER RICH");
		map1.set("AGE", null);
		
		Row map2 = new Row();
		map2.set("NAME", "PAUL POOR");
		map2.set("AGE", new Integer(38));
		
		List<Row> rows = new ArrayList<Row>();
		rows.add(map1);
		rows.add(map2);
		
		List<String> fields = new ArrayList<String>();
		fields.add("NAME");
		fields.add("AGE");
		
		ResultSet resultSet = new ResultSet(fields, rows);
		
		String formatted = resultSet.toString();
		
		assertEquals("NAME,AGE|NAME:PETER RICH,AGE:NULL|NAME:PAUL POOR,AGE:38|", formatted);
		
	}

}
