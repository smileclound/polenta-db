package com.polenta.db.object;

import java.util.Map;

public interface Metadata {

	public Map<String, String> retrieveMetadataForObject(String name);
	
}
