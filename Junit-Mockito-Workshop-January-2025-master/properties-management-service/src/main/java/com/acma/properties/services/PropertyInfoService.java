/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.PropertyInfoBean;

/**
 * 
 */
public interface PropertyInfoService {

	PropertyInfoBean createProperty(PropertyInfoBean type);
	PropertyInfoBean getPropertyInfoById(String id);
	List<PropertyInfoBean> getPropertyInfoByZipCode(String zipCode);
	
	List<PropertyInfoBean> getAllProperties();
	List<PropertyInfoBean> deletePropertyInfoById(String id);

	List<PropertyInfoBean> searchPropertiesByZipCode(String zipCode);

	
}
