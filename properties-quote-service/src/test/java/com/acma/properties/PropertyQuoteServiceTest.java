/**
 * 
 */
package com.acma.properties;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acma.properties.beans.CoverageTypeBean;
import com.acma.properties.beans.PerilsTypeBean;
import com.acma.properties.beans.PolicyTypeBean;
import com.acma.properties.beans.PropertyQuoteBean;
import com.acma.properties.beans.QuoteTypeBean;
import com.acma.properties.models.PropertyLead;
import com.acma.properties.repos.PropertyLeadRepo;
import com.acma.properties.services.PropertyQuoteService;

/**
 * 
 */
public class PropertyQuoteServiceTest extends PropertiesQuoteServiceApplicationTests {

	@Autowired
	private PropertyQuoteService quoteService;
	
	@Autowired
	private PropertyLeadRepo leadRepo;
	
	PropertyQuoteBean propertyQuoteBean = null;
	
	
	
	@BeforeEach
	public void init() {
		propertyQuoteBean = new PropertyQuoteBean();
		propertyQuoteBean.setUserId("8765");
		propertyQuoteBean.setPropertyInfo("1");
		
		QuoteTypeBean quoteTypeBean = new QuoteTypeBean();
		quoteTypeBean.setId(1);
		
		propertyQuoteBean.setQuoteType(quoteTypeBean);
		
		CoverageTypeBean coverageTypeBean = new CoverageTypeBean();
		coverageTypeBean.setId(1);
		
		PerilsTypeBean fireType = new PerilsTypeBean();
		fireType.setId(1);
		
//		PerilsTypeBean windType = new PerilsTypeBean();
//		windType.setId(2);
//		windType.setPerilsType("Wind");
		
		List<PerilsTypeBean> perilsList = new ArrayList<>();
		perilsList.add(fireType);
		//perilsList.add(windType);
		
		coverageTypeBean.setPerilsList(perilsList);
		
		propertyQuoteBean.setCoverageType(coverageTypeBean);
		
		PolicyTypeBean policyTypeBean = new PolicyTypeBean();
		policyTypeBean.setId(1);
		
		propertyQuoteBean.setPolicyType(policyTypeBean);
	}
	
    //@Test
    public void testQuoteCreation() {
    	PropertyQuoteBean savedQuoteBean = quoteService.createQuote(propertyQuoteBean);
    	assertNotNull(savedQuoteBean);
    	assertNotNull(savedQuoteBean.getId());	
	}
    
    public void testUpdateLead() {
    	PropertyLead dbLead = leadRepo.findById(2).get();
    	dbLead.setPropertyInfo("1234");
    	
    	leadRepo.save(dbLead);
    	assertNotNull(dbLead);
    	
    }
}
