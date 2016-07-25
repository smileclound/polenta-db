package com.polenta.db.object;

import java.lang.reflect.AnnotatedType;
import java.util.List;
import java.util.Map;

import com.polenta.db.exception.PolentaException;
import com.polenta.db.object.behavior.Alterable;
import com.polenta.db.object.behavior.Creatable;
import com.polenta.db.object.behavior.Deletable;
import com.polenta.db.object.behavior.Dropable;
import com.polenta.db.object.behavior.Insertable;
import com.polenta.db.object.behavior.Selectable;
import com.polenta.db.object.behavior.Updatable;
import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.User;

public class ObjectManager {
	
	@SuppressWarnings("rawtypes")
	public static Class retrieveObjectTypeClass(String objectType) {
		if (objectType.equalsIgnoreCase("BAG")) {
			return Bag.class;
		} else if (objectType.equalsIgnoreCase("USER")) { 
			return User.class; 
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isOperationSupportedByObjectType(Class objectTypeClazz, String operation) {
		Class operationClazz;
		if (operation.equalsIgnoreCase("ALTER")) {
			operationClazz = Alterable.class;
		} else if (operation.equalsIgnoreCase("CREATE")) {
			operationClazz = Creatable.class;
		} else if (operation.equalsIgnoreCase("DELETE")) {
			operationClazz = Deletable.class;
		} else if (operation.equalsIgnoreCase("DROP")) {
			operationClazz = Dropable.class;
		} else if (operation.equalsIgnoreCase("INSERT")) {
			operationClazz = Insertable.class;
		} else if (operation.equalsIgnoreCase("SELECT")) {
			operationClazz = Selectable.class;
		} else if (operation.equalsIgnoreCase("UPDATE")) {
			operationClazz = Updatable.class;
		} else {
			return false;
		}
		for (AnnotatedType type: objectTypeClazz.getAnnotatedInterfaces()) {
			if (type.getType().equals(operationClazz)) {
				return true;
			}
		}
		return false;
	}
	
	//refactor this entire block below to avoid if's
	
	public static void performAlter(Class clazz, Map<String, Object> newDefinitions) {
	}
	
	public static void performCreate(Class clazz, String name, Map<String, KeyValue> definitionValues) throws PolentaException {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().create(name, definitionValues);
		}
		if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().create(name, definitionValues);
		}
	}

	public static void performDelete(Class clazz, Map<String, Object> filterValues) {
		if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().delete(filterValues);
		}
	}

	public static void performDrop(Class clazz, String objectName) {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().drop(objectName);
		}
	}

	public static void performInsert(Class clazz, Map<String, Object> insertValues) {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().insert(insertValues);
		}
	}

	public static void performSelect(Class clazz, List<String> returnFields, Map<String, Object> filterValues) {
		if (Bag.class.isAssignableFrom(clazz)) {
			Bag.getInstance().select(returnFields, filterValues);
		}
		if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().select(returnFields, filterValues);
		}
	}

	public static void performUpdate(Class clazz, Map<String, Object> filterValues, Map<String, Object> newValues) {
		if (User.class.isAssignableFrom(clazz)) {
			User.getInstance().update(filterValues, newValues);
		}
	}

}
