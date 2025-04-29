/**
 * 
 */
package com.acma.properties.beans;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

/**
 * 
 */
@Data
public class PropertyInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9192386814955682637L;
	
	@Id
	private String id;
	
	private String sqFootage;
	private int yearBuilt;
	private int noOfFloors;
	private String appraisedValue;
	private String replacementCost;
	private String marketValue;
	
	private PropertyTypeBean propertyTypeBean;
	
	private AddressBean addressBean;
	
    private ConstructionTypeBean constructionTypeBean;
    private OccupancyTypeBean occupancyTypeBean;

}
