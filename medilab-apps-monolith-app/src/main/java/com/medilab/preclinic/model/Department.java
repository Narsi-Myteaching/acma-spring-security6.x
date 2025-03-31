/**
 * 
 */
package com.medilab.preclinic.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;

/**
 * @author nsanda
 *
 */
@Entity
@Data
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1839672696590120994L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int deptId;
	
	@Column(unique=true,nullable=false)
	private String name;
	private String description;
	
	@Column(nullable=false)
	private String status;

}
