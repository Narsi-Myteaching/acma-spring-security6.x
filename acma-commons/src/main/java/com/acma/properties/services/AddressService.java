/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.AddressBean;

/**
 * 
 */
public interface AddressService {

	AddressBean addOrUpdateAddress(AddressBean addressBean);
	List<AddressBean> lokupAddressByZipCode(String zipCode);
}
