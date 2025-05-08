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
public class EmailRequestBean implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String userId;
	private int id;
	private String status;
	

}
