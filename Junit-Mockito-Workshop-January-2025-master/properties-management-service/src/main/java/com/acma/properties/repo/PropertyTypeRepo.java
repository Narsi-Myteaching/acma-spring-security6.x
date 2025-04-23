/**
 * 
 */
package com.acma.properties.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.acma.properties.models.PropertyType;

/**
 * 
 */
public interface PropertyTypeRepo extends MongoRepository<PropertyType, String> {

}
