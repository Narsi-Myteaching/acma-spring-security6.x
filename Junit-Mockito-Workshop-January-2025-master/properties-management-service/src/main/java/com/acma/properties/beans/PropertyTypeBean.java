package com.acma.properties.beans;

import java.io.Serializable;

import lombok.Data;

@Data
public class PropertyTypeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7791909992847027618L;
	
	private String id;
	
	private String type;

}
