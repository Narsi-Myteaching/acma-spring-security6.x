/**
 * 
 */
package com.acma.properties.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.acma.properties.beans.PropertyTypeBean;
import com.acma.properties.models.PropertyType;
import com.acma.properties.repo.PropertyTypeRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
public class PropertyTypeServiceImpl implements PropertyTypeService {

	private PropertyTypeRepo propertyTypeRepo;
	
	private ModelMapper modelMapper;
	
	public PropertyTypeServiceImpl(PropertyTypeRepo propertyTypeRepo,ModelMapper modelMapper) {
		this.propertyTypeRepo = propertyTypeRepo;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public PropertyTypeBean createPropertyType(PropertyTypeBean type) {
		log.info("----PrpertiesType is Creating-->");
		log.info("PropertiesType:\t"+type.toString());
		PropertyType propertyType = modelMapper.map(type, PropertyType.class);
		try {
			propertyType = propertyTypeRepo.save(propertyType);
			log.info("---PropertiesType Created-->");
			log.info("ID:\t"+propertyType.getId());
			if(propertyType != null && StringUtils.hasText(propertyType.getId())) {
				return  modelMapper.map(propertyType,PropertyTypeBean.class);
			}else {
				throw new RuntimeException("Issue in Creating the Property Type");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public PropertyTypeBean getPropertyType(String id) {
		log.info("--Fetching PropertyType For the Id-->");
		log.info("ID:\t"+id);
		if(StringUtils.hasText(id)) {
			return modelMapper.map(propertyTypeRepo.findById(id).get(),PropertyTypeBean.class);
		}else {
			throw new RuntimeException("Provided id is not valid");
		}
	}


	@Override
	public List<PropertyTypeBean> getAllPropertyTypes() {
		return propertyTypeRepo.findAll().stream().map(propertyType->modelMapper.map(propertyType, PropertyTypeBean.class)).collect(Collectors.toList());
		
	}
	
	
	
	

}
