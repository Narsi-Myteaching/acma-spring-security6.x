/**
 * 
 */
package com.acma.properties;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acma.properties.beans.QuoteTypeBean;
import com.acma.properties.services.QuoteTypeService;

/**
 * 
 */
public class PropertyQuoteTypeServiceTest extends PropertiesQuoteServiceApplicationTests {

	@Autowired
	private QuoteTypeService quoteTypeService;
	
	QuoteTypeBean quoteTypeBean = null;
	
	@BeforeEach
	public void init() {
		quoteTypeBean = new QuoteTypeBean();
		quoteTypeBean.setType("FullApplication");
	}
	
	@Test
	public void testQuoteCreation() {
		QuoteTypeBean savedQuoteTypeBean = quoteTypeService.createQuote(quoteTypeBean);
		assertNotNull(savedQuoteTypeBean);
		assertNotNull(savedQuoteTypeBean.getId());
		assertEquals(quoteTypeBean.getType(), savedQuoteTypeBean.getType());
		
	}
}
