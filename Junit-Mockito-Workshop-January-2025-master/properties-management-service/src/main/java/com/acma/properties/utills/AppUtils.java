package com.acma.properties.utills;

import com.acma.properties.beans.*;
import com.acma.properties.models.PropertyInfo;
import org.modelmapper.ModelMapper;

import java.util.List;

public class AppUtils {

    public static PropertyInfoBean mapDomainToBean(PropertyInfo propertyInfoModel, ModelMapper modelMapper) {
        PropertyInfoBean propertyInfoBean = modelMapper.map(propertyInfoModel, PropertyInfoBean.class);
        if (propertyInfoModel != null && propertyInfoModel.getPropertyType() != null) {
            propertyInfoBean
                    .setPropertyTypeBean(modelMapper.map(propertyInfoModel.getPropertyType(), PropertyTypeBean.class));
        }
        if (propertyInfoModel != null && propertyInfoModel.getConstructionType() != null) {
            propertyInfoBean.setConstructionTypeBean(
                    modelMapper.map(propertyInfoModel.getConstructionType(), ConstructionTypeBean.class));
        }
        if (propertyInfoModel != null && propertyInfoModel.getOccupancyType() != null) {
            propertyInfoBean.setOccupancyTypeBean(
                    modelMapper.map(propertyInfoModel.getOccupancyType(), OccupancyTypeBean.class));
        }
        if (propertyInfoModel != null && propertyInfoModel.getAddress() != null) {
            propertyInfoBean.setAddressBean(modelMapper.map(propertyInfoModel.getAddress(), AddressBean.class));
        }
        return propertyInfoBean;
    }

    public static boolean hasSize(List<PropertyInfo> dbPropertiesInfoList) {
        return  dbPropertiesInfoList.size() > 0 ? true : false;
    }
}
