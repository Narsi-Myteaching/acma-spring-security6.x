/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class PropertyLeadDetails implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 6739162956905282293L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private PropertyLead lead;
	
	@ManyToOne
	private PropertyQuote quote;
}
