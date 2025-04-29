/**
 * 
 */
package com.acma.properties.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acma.properties.beans.PropertyQuoteBean;
import com.acma.properties.services.PropertyQuoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RestController
@Slf4j
@AllArgsConstructor
public class PropertyQuoteResource {

	private PropertyQuoteService quoteService;

	@CrossOrigin(allowedHeaders = "*")
	@PostMapping(value = { "/property-quote", "/property-quote/" })
	@Operation(description = "createQuote", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<PropertyQuoteBean> createQuote(@RequestBody PropertyQuoteBean propertyQuoteBean) {
		log.info("--CreatingQuote--->");
		log.info(propertyQuoteBean.toString());

		// service method call--->business delegate

		return ResponseEntity.ok(quoteService.createQuote(propertyQuoteBean));
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(value = { "/property-quote", "/property-quote/" })
	@Operation(description = "getAllQuotes", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<PropertyQuoteBean>> getAllQuotes() {
		List<PropertyQuoteBean> emptyList = new ArrayList<>();
		log.info("--Get all Quotes--->");
		List<PropertyQuoteBean> dbQuotesList = quoteService.getAllPropertyQuotes();
		if(!CollectionUtils.isEmpty(dbQuotesList)) {
			return ResponseEntity.ok(dbQuotesList);
		}else {
			return ResponseEntity.ok(emptyList);
		}
		
	}

	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(value = { "/property-quote/{quoteId}" })
	@Operation(description = "getQuoteById", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<PropertyQuoteBean> getQuoteById(@PathVariable("quoteId") int quoteId) {
		log.info("--getQuoteById--->:\t" + quoteId);

		// service method call--->business delegate

		return ResponseEntity.ok(quoteService.getQuoteById(quoteId));
	}

	@CrossOrigin(allowedHeaders = "*")
	@DeleteMapping(value = { "/property-quote/{quoteId}" })
	@Operation(description = "deleteQuoteById", security = @SecurityRequirement(name = "bearerAuth"))
	public ResponseEntity<List<PropertyQuoteBean>> deleteQuoteById(@PathVariable("quoteId") int quoteId) {
		log.info("--getQuoteById--->:\t" + quoteId);

		// service method call--->business delegate

		return ResponseEntity.ok(quoteService.deleteQuote(quoteId));
	}
}
