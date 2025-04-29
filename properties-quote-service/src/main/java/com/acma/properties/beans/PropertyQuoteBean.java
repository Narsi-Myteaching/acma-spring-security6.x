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
public class PropertyQuoteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4175617473584564895L;
	
	private int id;
	
	private String name;
	private String userId;
	private String propertyInfo;
	
	private QuoteTypeBean quoteType;
	
	private CoverageTypeBean coverageType;
	
	private PolicyTypeBean policyType;

}
