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
public class PolicyTypeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9190371397066087837L;

	private int id;
	
	private String policyType;
	
	private CoverageTypeBean covType;
	
}
