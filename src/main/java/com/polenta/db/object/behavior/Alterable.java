package com.polenta.db.object.behavior;

import java.util.Map;

public interface Alterable {

	public void alter(Map<String, Object> newDefinitions);

}
