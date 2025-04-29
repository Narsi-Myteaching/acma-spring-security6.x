/**
 * 
 */
package com.acma.properties.services;

import com.acma.properties.beans.ConstructionTypeBean;

/**
 * 
 */
public interface ConstructionTypeService {
  ConstructionTypeBean createConstructionType(ConstructionTypeBean cTypeBean);
  ConstructionTypeBean getConstructionByType(String type);
  ConstructionTypeBean getConstructionById(String id);
}
