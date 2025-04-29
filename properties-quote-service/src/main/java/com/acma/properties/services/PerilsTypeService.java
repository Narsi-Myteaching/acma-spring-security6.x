/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.PerilsTypeBean;

/**
 * 
 */
public interface PerilsTypeService {

	PerilsTypeBean createPerils(PerilsTypeBean perilsTypeBean);
	PerilsTypeBean getPerilsById(int id);
	List<PerilsTypeBean> getAllPerilsTypes();
}
