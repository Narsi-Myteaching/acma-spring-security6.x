/**
 * 
 */
package com.acma.properties.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.acma.properties.models.PropertyInfo;

/**
 * 
 */
public interface PropertyInfoRepo extends MongoRepository<PropertyInfo, String> {
	
	//@Query("from PropertyInfo p where p.address.zipCode=:zipCode")
	//@Query("{ 'from PropertyInfo p where p.address.zipCode' : ?0 }")
	@Query("{ 'address.zipCode' : ?0 }")
	public List<PropertyInfo> findPropertiesInZipCode(String zipCode);
}
