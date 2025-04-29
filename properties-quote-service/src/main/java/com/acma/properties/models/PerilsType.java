/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class PerilsType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8888764505127372855L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,unique = true)
	private String perilsType;
	
	
}
