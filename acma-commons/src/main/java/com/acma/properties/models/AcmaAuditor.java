/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.EntityListeners;
import lombok.Data;

/**
 * 
 */
@Data
//@EntityListeners(value = { AcmaAuditor.class })
public class AcmaAuditor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6131122269254278242L;
	private Date createdDate;
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
}
