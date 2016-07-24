package com.polenta.db.object;

import org.junit.Test;

import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

import static org.junit.Assert.*;

public class ObjectManagerTest {
	
	ObjectManager manager = new ObjectManager();
	
	@Test
	public void testBagOperationByObjectTypeClass() {
		assertFalse(manager.isOperationSupportedByObjectType(Bag.class, "ALTER"));
		assertTrue(manager.isOperationSupportedByObjectType(Bag.class, "CREATE"));
		assertFalse(manager.isOperationSupportedByObjectType(Bag.class, "DELETE"));
		assertTrue(manager.isOperationSupportedByObjectType(Bag.class, "DROP"));
		assertTrue(manager.isOperationSupportedByObjectType(Bag.class, "INSERT"));
		assertTrue(manager.isOperationSupportedByObjectType(Bag.class, "SELECT"));
		assertFalse(manager.isOperationSupportedByObjectType(Bag.class, "UPDATE"));
	}
	
	@Test
	public void testUserOperationByObjectTypeClass() {
		assertFalse(manager.isOperationSupportedByObjectType(User.class, "ALTER"));
		assertTrue(manager.isOperationSupportedByObjectType(User.class, "CREATE"));
		assertTrue(manager.isOperationSupportedByObjectType(User.class, "DELETE"));
		assertFalse(manager.isOperationSupportedByObjectType(User.class, "DROP"));
		assertFalse(manager.isOperationSupportedByObjectType(User.class, "INSERT"));
		assertTrue(manager.isOperationSupportedByObjectType(User.class, "SELECT"));
		assertTrue(manager.isOperationSupportedByObjectType(User.class, "UPDATE"));
	}

}
