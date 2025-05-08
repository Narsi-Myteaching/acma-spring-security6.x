/**
 * 
 */
package com.acma.properties.beans;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 */
@Data
public class AddressBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7469005006005771546L;
	private String address1;
	private String address2;
	private String zipCode;
	private String city;
	private String state;
	private String country;
	
	private String id;
	
}
