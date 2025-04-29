/**
 * 
 */
package com.acma.properties.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 
 */
@FeignClient(name = "properties-management-svc", url = "${acma.properties.base-uri}")
public interface PropertiesMgmtSvcFeign {

	@GetMapping(value = {"/propertyInfo/{propId}"})
	String fetchPropertyInfoById(@PathVariable("propId") String propId);
}
