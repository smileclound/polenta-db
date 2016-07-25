package com.polenta.db.object;

import org.junit.Test;

import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

import static org.junit.Assert.*;

public class ObjectManagerTest {
	
	@Test
	public void testBagOperationByObjectTypeClass() {
		assertFalse(ObjectManager.isOperationSupportedByObjectType(Bag.class, "ALTER"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(Bag.class, "CREATE"));
		assertFalse(ObjectManager.isOperationSupportedByObjectType(Bag.class, "DELETE"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(Bag.class, "DROP"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(Bag.class, "INSERT"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(Bag.class, "SELECT"));
		assertFalse(ObjectManager.isOperationSupportedByObjectType(Bag.class, "UPDATE"));
	}
	
	@Test
	public void testUserOperationByObjectTypeClass() {
		assertFalse(ObjectManager.isOperationSupportedByObjectType(User.class, "ALTER"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(User.class, "CREATE"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(User.class, "DELETE"));
		assertFalse(ObjectManager.isOperationSupportedByObjectType(User.class, "DROP"));
		assertFalse(ObjectManager.isOperationSupportedByObjectType(User.class, "INSERT"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(User.class, "SELECT"));
		assertTrue(ObjectManager.isOperationSupportedByObjectType(User.class, "UPDATE"));
	}

}
