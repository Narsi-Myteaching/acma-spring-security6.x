/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

/**
 * 
 */
@Entity
@Data
public class CoverageType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9201174566691552173L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,unique = true)
	private String coverageType;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "coverageTypeModel")
	private List<CoverageToPerilsType> perilsList;
	
}
