/**
 * 
 */
package com.acma.properties.models;

import java.time.LocalDate;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditTable(value = "AU_PROPERTY_LEAD")
public class PropertyLead extends AcmaAuditor  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4988766093533869253L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String userInfo;
	private String propertyInfo;
	private String status;
	
	
	@Temporal(TemporalType.DATE)
	private LocalDate startDate;
	
	@Temporal(TemporalType.DATE)
	private LocalDate expDate;
	
	
}
