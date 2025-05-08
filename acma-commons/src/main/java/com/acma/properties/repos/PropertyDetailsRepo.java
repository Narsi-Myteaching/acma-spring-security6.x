/**
 * 
 */
package com.acma.properties.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acma.properties.models.PropertyQuoteDetails;

/**
 * 
 */
public interface PropertyDetailsRepo extends JpaRepository<PropertyQuoteDetails, Integer> {

	@Query("from PropertyQuoteDetails pqd where pqd.propertyQuote.id=:quoteId")
	List<PropertyQuoteDetails> findAllQuotesByQuote(@Param("quoteId") int quoteId);
	
}
