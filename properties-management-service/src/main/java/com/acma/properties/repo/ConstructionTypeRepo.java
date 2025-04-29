/**
 * 
 */
package com.acma.properties.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.acma.properties.models.ConstructionType;

/**
 * 
 */
public interface ConstructionTypeRepo extends MongoRepository<ConstructionType, String> {

	//@Query(value = "from ConstructionType ctype where ctype.type=:cType")
	@Query("{'type' : ?0 }")
	public List<ConstructionType> fetchCoonstructionByType(String type);
}
