/**
 * 
 */
package com.acma.properties.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * 
 */
@Document(collection = "${properties.management.collections.name}")
@Data
public class PropertyInfo {

	@Id
	private String id;
	
	private String sqFootage;
	private int yearBuilt;
	private int noOfFloors;
	private String appraisedValue;
	private String replacementCost;
	private String marketValue;
	
	private PropertyType propertyType;
	
	private PropertiesAddress address;
	
	private ConstructionType constructionType;
	private OccupancyType occupancyType;
	
//	private String propertyType;
//	private String addressRef;
//	private String constructionType;
//	private String occupancyType;
	
	
}
