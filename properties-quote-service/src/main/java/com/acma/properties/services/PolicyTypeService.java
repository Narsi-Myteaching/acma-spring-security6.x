/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.PolicyTypeBean;

/**
 * 
 */
public interface PolicyTypeService {

	PolicyTypeBean createPolicy(PolicyTypeBean PolicyTypeBean);
	PolicyTypeBean getPolicyById(int id);
	List<PolicyTypeBean> getAllPolicyTypes();
}
