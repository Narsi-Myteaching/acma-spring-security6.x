/**
 * 
 */
package com.acma.properties.beans;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyLeadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4988766093533869253L;
	
	private int id;
	
	private String userInfo;
	private String propertyInfo;
	private String status;
	
	private String expDate;
	
	private String quoteName;
	private QuoteTypeBean quoteType;
	
	private CoverageTypeBean coverageType;
	
	private PolicyTypeBean policyType;

}
