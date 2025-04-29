/**
 * 
 */
package com.acma.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.acma.properties.beans.CoverageTypeBean;
import com.acma.properties.beans.PerilsTypeBean;
import com.acma.properties.services.CoverageTypeService;

/**
 * 
 */
public class CoverageTypeServiceTest extends PropertiesQuoteServiceApplicationTests {

	@Autowired
	private CoverageTypeService coverageTypeService;
	
	CoverageTypeBean coverageTypeBean = null;
	
	@BeforeEach
	public void init() {
		coverageTypeBean = new CoverageTypeBean();
		coverageTypeBean.setCoverageType("Basic");
		
		PerilsTypeBean fireType = new PerilsTypeBean();
		fireType.setId(1);
		fireType.setPerilsType("Fire");
		
//		PerilsTypeBean windType = new PerilsTypeBean();
//		windType.setId(2);
//		windType.setPerilsType("Wind");
		
		List<PerilsTypeBean> perilsList = new ArrayList<>();
		perilsList.add(fireType);
		//perilsList.add(windType);
		
		coverageTypeBean.setPerilsList(perilsList);
	}
	
	@Test
	public void testQuoteCreation() {
		CoverageTypeBean savedCoverageTypeBean = coverageTypeService.createCoverage(coverageTypeBean);
		assertNotNull(savedCoverageTypeBean);
		assertNotNull(savedCoverageTypeBean.getId());
		assertEquals(coverageTypeBean.getCoverageType(), savedCoverageTypeBean.getCoverageType());		
	}
}
