/**
 * 
 */
package com.acma.properties.beans;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 */
@Data
public class CoverageTypeBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9190371397066087837L;

	private int id;
	
	private String coverageType;
	
	private List<PerilsTypeBean> perilsList;
	
}
