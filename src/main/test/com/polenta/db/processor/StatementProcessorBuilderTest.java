package com.polenta.db.processor;

import org.junit.Test;
import static org.junit.Assert.*;

import com.polenta.db.exception.InvalidStatementException;

public class StatementProcessorBuilderTest {

	@Test
	public void testCreateBag() throws InvalidStatementException {
		StatementProcessor processor = StatementProcessorBuilder.build("CREATE BAG ()");
		assertTrue(processor instanceof CreateBagProcessor);
	}
	
}
