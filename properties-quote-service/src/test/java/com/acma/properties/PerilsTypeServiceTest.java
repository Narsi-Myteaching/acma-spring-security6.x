/**
 * 
 */
package com.acma.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acma.properties.beans.PerilsTypeBean;
import com.acma.properties.services.PerilsTypeService;

/**
 * 
 */
public class PerilsTypeServiceTest extends PropertiesQuoteServiceApplicationTests {

	@Autowired
	private PerilsTypeService perilsTypeService;
	
	PerilsTypeBean perilsTypeBean = null;
	
	@BeforeEach
	public void init() {
		perilsTypeBean = new PerilsTypeBean();
		perilsTypeBean.setPerilsType("Fire");
	}
	
	@Test
	public void testQuoteCreation() {
		PerilsTypeBean savedPerilsTypeBean = perilsTypeService.createPerils(perilsTypeBean);
		assertNotNull(savedPerilsTypeBean);
		assertNotNull(savedPerilsTypeBean.getId());
		assertEquals(perilsTypeBean.getPerilsType(), savedPerilsTypeBean.getPerilsType());
		
	}
}
