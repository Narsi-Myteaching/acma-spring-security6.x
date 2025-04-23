/**
 * 
 */
package com.acma.properties.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * 
 */
@Document(collection = "${properties.management.collections.name}")
@Data
public class PropertiesAddress extends Address {

	@Id
	private String id;
}
