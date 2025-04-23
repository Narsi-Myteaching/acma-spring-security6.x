/**
 * 
 */
package com.acma.properties.resources;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.acma.properties.beans.PropertyInfoBean;
import com.acma.properties.services.PropertyInfoService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@RestController
@Slf4j
@AllArgsConstructor
public class PropertiesManagementResource {

	private PropertyInfoService propertyInfoService;
	
	@CrossOrigin(allowedHeaders = "*")
	@PostMapping(value = {"/propertyInfo"})
	public ResponseEntity<PropertyInfoBean> savePropertyInfo(@RequestBody PropertyInfoBean propertyInfoBean){
		log.info("---PropertyInfo is saving-->");
		log.info("PropertyInfo is "+propertyInfoBean.toString());
		propertyInfoBean =  propertyInfoService.createProperty(propertyInfoBean);
		return ResponseEntity.ok(propertyInfoBean);
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(value = {"/propertyInfo"})
	public ResponseEntity<List<PropertyInfoBean>> getAllPropertiesInfo(){
		log.info("---Fetching all Properties Information-->");
		List<PropertyInfoBean> propertiesList = propertyInfoService.getAllProperties();
		return ResponseEntity.ok(propertiesList);
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@GetMapping(value = {"/propertyInfo/{propertyInfoId}"})
	public ResponseEntity<PropertyInfoBean> getPropetyById(@PathVariable("propertyInfoId") String id){
		log.info("---Fetching a Property Information BY Id-->:\t"+id);
		return ResponseEntity.ok(propertyInfoService.getPropertyInfoById(id));
	}
	
	@CrossOrigin(allowedHeaders = "*")
	@DeleteMapping(value = {"/propertyInfo/{propertyInfoId}"})
	public ResponseEntity<List<PropertyInfoBean>>  deletePropetyById(@PathVariable("propertyInfoId") String id){
		log.info("---Fetching a Property Information BY Id-->:\t"+id);
		return ResponseEntity.ok(propertyInfoService.deletePropertyInfoById(id));
	}
	
	@GetMapping(value = {"/propertyInfo/zipcode/{zipCode}"})
	public ResponseEntity<List<PropertyInfoBean>> getPropetyByZipCode(@PathVariable("zipCode") String zipCode){
		log.info("---Fetching a Property Information BY zipCode-->:\t"+zipCode);
		List<PropertyInfoBean> propertiesList = propertyInfoService.getPropertyInfoByZipCode(zipCode);
		return ResponseEntity.ok(propertiesList);
	}
	
}
