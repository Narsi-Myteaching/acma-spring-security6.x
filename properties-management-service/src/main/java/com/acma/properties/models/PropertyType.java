/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 */
//@Document(collection = "properties-management")
//@Document(collection = "${properties.management.collections.name}")
@Data
public class PropertyType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4261693769213719662L;

	//@Id
	private String id;
	
	private String type;
}
