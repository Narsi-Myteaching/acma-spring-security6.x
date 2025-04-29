/**
 * 
 */
package com.acma.properties.services;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.acma.properties.utills.AppUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.acma.properties.beans.AddressBean;
import com.acma.properties.beans.ConstructionTypeBean;
import com.acma.properties.beans.OccupancyTypeBean;
import com.acma.properties.beans.PropertyInfoBean;
import com.acma.properties.beans.PropertyTypeBean;
import com.acma.properties.models.PropertyInfo;
import com.acma.properties.repo.PropertyInfoRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
@AllArgsConstructor
public class PropertyInfoServiceImpl implements PropertyInfoService {

	private PropertyInfoRepo propertyInfoRepo;

	private ModelMapper modelMapper;

	private SearchService searchService;

	@Override
	public PropertyInfoBean createProperty(PropertyInfoBean type) {
		PropertyInfo propertyInfoModel = modelMapper.map(type, PropertyInfo.class);
		log.info("PropertyINfo Model is :" + propertyInfoModel.toString());

		propertyInfoModel = propertyInfoRepo.save(propertyInfoModel);
		PropertyInfoBean propertyInfoBean = AppUtils.mapDomainToBean(propertyInfoModel,modelMapper);
		return propertyInfoBean;
	}

	@Override
	public PropertyInfoBean getPropertyInfoById(String id) {
		try {
			PropertyInfo propertyInfoModel = propertyInfoRepo.findById(id).get();
			if (!ObjectUtils.isEmpty(propertyInfoModel)) {
				return AppUtils.mapDomainToBean(propertyInfoModel,modelMapper);
			} else {
				throw new RuntimeException("No Property Info Found with the Id " + id);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}

	}
	
	@Override
	public List<PropertyInfoBean> deletePropertyInfoById(String id) {
		try {
			propertyInfoRepo.deleteById(id);
			return getAllProperties();
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}

	}

	@Override
	public List<PropertyInfoBean> searchPropertiesByZipCode(String zipCode) {
		return searchService.searchByName(zipCode);
	}

	@Override
	public List<PropertyInfoBean> getAllProperties() {
		try {
			List<PropertyInfo> dbPropertiesInfoList = propertyInfoRepo.findAll();
			if ((!CollectionUtils.isEmpty(dbPropertiesInfoList)) && (dbPropertiesInfoList.size() > 0)) {
				return dbPropertiesInfoList.stream().map(propertyInfoModel -> AppUtils.mapDomainToBean(propertyInfoModel,modelMapper))
						.sorted(Comparator.comparing(PropertyInfoBean::getId).reversed())
						.collect(Collectors.toList());
			} else {
				throw new RuntimeException("No Properties found");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}

	}

//	private PropertyInfoBean mapDomainToBean(PropertyInfo propertyInfoModel) {
//		PropertyInfoBean propertyInfoBean = modelMapper.map(propertyInfoModel, PropertyInfoBean.class);
//		if (propertyInfoModel != null && propertyInfoModel.getPropertyType() != null) {
//			propertyInfoBean
//					.setPropertyTypeBean(modelMapper.map(propertyInfoModel.getPropertyType(), PropertyTypeBean.class));
//		}
//		if (propertyInfoModel != null && propertyInfoModel.getConstructionType() != null) {
//			propertyInfoBean.setConstructionTypeBean(
//					modelMapper.map(propertyInfoModel.getConstructionType(), ConstructionTypeBean.class));
//		}
//		if (propertyInfoModel != null && propertyInfoModel.getOccupancyType() != null) {
//			propertyInfoBean.setOccupancyTypeBean(
//					modelMapper.map(propertyInfoModel.getOccupancyType(), OccupancyTypeBean.class));
//		}
//		if (propertyInfoModel != null && propertyInfoModel.getAddress() != null) {
//			propertyInfoBean.setAddressBean(modelMapper.map(propertyInfoModel.getAddress(), AddressBean.class));
//		}
//		return propertyInfoBean;
//	}

	@Override
	public List<PropertyInfoBean> getPropertyInfoByZipCode(String zipCode) {
		try {
			List<PropertyInfo> dbPropertiesInfoList = propertyInfoRepo.findPropertiesInZipCode(zipCode);
			if (!CollectionUtils.isEmpty(dbPropertiesInfoList) && dbPropertiesInfoList.size() > 0) {
				return dbPropertiesInfoList.stream().map(propertyInfoModel -> AppUtils.mapDomainToBean(propertyInfoModel,modelMapper))
						.collect(Collectors.toList());
			} else {
				throw new RuntimeException("No Properties found with the zipCode "+zipCode);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}


}
