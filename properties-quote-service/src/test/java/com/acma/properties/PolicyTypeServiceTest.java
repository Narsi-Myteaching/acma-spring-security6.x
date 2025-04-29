/**
 * 
 */
package com.acma.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acma.properties.beans.CoverageTypeBean;
import com.acma.properties.beans.PolicyTypeBean;
import com.acma.properties.services.PolicyTypeService;

/**
 * 
 */
public class PolicyTypeServiceTest extends PropertiesQuoteServiceApplicationTests {

	@Autowired
	private PolicyTypeService policyTypeService;
	
	PolicyTypeBean policyTypeBean = null;
	
	@BeforeEach
	public void init() {
		CoverageTypeBean coverageTypeBean = new CoverageTypeBean();
		coverageTypeBean.setId(1);
		coverageTypeBean.setCoverageType("Standard");
		
		policyTypeBean = new PolicyTypeBean();
		policyTypeBean.setPolicyType("HO-3");
		policyTypeBean.setCovType(coverageTypeBean);
		
		
		
	}
	
	@Test
	public void testQuoteCreation() {
		PolicyTypeBean savedPolicyTypeBean = policyTypeService.createPolicy(policyTypeBean);
		assertNotNull(savedPolicyTypeBean);
		assertNotNull(savedPolicyTypeBean.getId());
		assertEquals(policyTypeBean.getPolicyType(), savedPolicyTypeBean.getPolicyType());
		assertNotNull(savedPolicyTypeBean.getCovType());
		assertEquals(policyTypeBean.getCovType().getCoverageType(), savedPolicyTypeBean.getCovType().getCoverageType());
	}
}
