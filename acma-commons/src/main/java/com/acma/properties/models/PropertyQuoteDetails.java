/**
 * 
 */
package com.acma.properties.models;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * 
 */
@Entity
public class PropertyQuoteDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5679658814246614649L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private PropertyQuote propertyQuote;
	
	@ManyToOne
	private QuoteType quoteType;
	
	@ManyToOne
	private CoverageType coverageType;
	
	@ManyToOne
	private PolicyType policyType;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PropertyQuote getPropertyQuote() {
		return propertyQuote;
	}

	public void setPropertyQuote(PropertyQuote propertyQuote) {
		this.propertyQuote = propertyQuote;
	}

	public QuoteType getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(QuoteType quoteType) {
		this.quoteType = quoteType;
	}

	public CoverageType getCoverageType() {
		return coverageType;
	}

	public void setCoverageType(CoverageType coverageType) {
		this.coverageType = coverageType;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}
	
	
}
