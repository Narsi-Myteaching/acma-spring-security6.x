/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 
 */
@Entity
@Table(name = "policyType")
@Data
public class PolicyType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6951448965947310475L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(nullable = false, unique = true)
	private String policyType;

	@ManyToOne(fetch = FetchType.EAGER)
	private CoverageType coverageType;

}
