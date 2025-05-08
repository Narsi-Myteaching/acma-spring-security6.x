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
public class QuoteTypeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9190371397066087837L;

	private int id;
	
	private String type;
	
}
