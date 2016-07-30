package com.polenta.db.object;

import com.polenta.db.object.type.Bag;
import com.polenta.db.object.type.Table;
import com.polenta.db.object.type.User;
import com.polenta.db.store.Storable;

public enum ObjectType {

	USER, BAG, TABLE;
	
	public Storable create() {
		if (this == BAG) {
			return new Bag();
		} else if (this == TABLE) {
			return new Table();
		} else if (this == USER) {
			return new User();
		}
		return null;
	}
	
}
