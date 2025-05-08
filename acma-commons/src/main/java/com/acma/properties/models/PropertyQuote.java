/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;
import java.time.LocalDate;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
@Audited
@AuditTable(value = "AU_PROPERTY_QUOTE")
public class PropertyQuote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8126780436059938331L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	private String userInfo;
	private String propertyInfo;
	
	@Temporal(TemporalType.DATE)
	private LocalDate createdDate;
	
	@Temporal(TemporalType.DATE)
	private LocalDate expDate;
	
	
}
