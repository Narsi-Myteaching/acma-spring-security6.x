/**
 * 
 */
package com.acma.properties.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.acma.properties.models.PropertyLeadDetails;

/**
 * 
 */
public interface PropertyLeadDetailsRepo extends JpaRepository<PropertyLeadDetails, Integer> {

	@Query("from PropertyLeadDetails pld where pld.quote.id=:quoteId")
	List<PropertyLeadDetails> findAllLeadsByQuote(@Param("quoteId") int quoteId);
	
	@Query("from PropertyLeadDetails pld where pld.lead.id=:leadId")
	List<PropertyLeadDetails> findAllLeadsById(@Param("leadId") int leadId);
}
