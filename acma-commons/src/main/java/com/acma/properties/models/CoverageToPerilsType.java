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
import jakarta.persistence.Table;

/**
 * 
 */
@Entity
@Table(name = "coverage_perils")
public class CoverageToPerilsType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8902081616717410666L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CoverageType getCoverageTypeModel() {
		return coverageTypeModel;
	}

	public void setCoverageTypeModel(CoverageType coverageTypeModel) {
		this.coverageTypeModel = coverageTypeModel;
	}

	public PerilsType getPerilsTypeModel() {
		return perilsTypeModel;
	}

	public void setPerilsTypeModel(PerilsType perilsTypeModel) {
		this.perilsTypeModel = perilsTypeModel;
	}

	@ManyToOne
	private CoverageType coverageTypeModel;
	
	@ManyToOne
	private PerilsType perilsTypeModel;
	
}
