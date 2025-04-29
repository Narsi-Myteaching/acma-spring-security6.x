/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.PropertyTypeBean;

/**
 * 
 */
public interface PropertyTypeService {

	PropertyTypeBean createPropertyType(PropertyTypeBean type);
	PropertyTypeBean getPropertyType(String id);
	
	List<PropertyTypeBean> getAllPropertyTypes();
	
}
