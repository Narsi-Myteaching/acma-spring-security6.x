/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.CoverageTypeBean;

/**
 * 
 */
public interface CoverageTypeService {

	CoverageTypeBean createCoverage(CoverageTypeBean coverageTypeBean);
	CoverageTypeBean getCoverageById(int id);
	List<CoverageTypeBean> getAllCoverageTypes();
}
