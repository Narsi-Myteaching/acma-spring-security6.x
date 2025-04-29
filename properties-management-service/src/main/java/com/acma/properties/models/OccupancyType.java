/**
 * 
 */
package com.acma.properties.models;

import lombok.Data;

/**
 * 
 */
//@Document(collection = "${properties.management.collections.name}")
@Data
public class OccupancyType {

	//@Id
	String id;
	
	String type;
}
