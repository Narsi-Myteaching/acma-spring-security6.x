package com.acma.properties.models;

import lombok.Data;

//@Document(collection = "${properties.management.collections.name}")
@Data
public class ConstructionType {

	//@Id
	private String id;
	
	private String type;
}
