/**
 *  ConstructionTypeService .
 */

package com.acma.properties.services;

import com.acma.properties.beans.ConstructionTypeBean;
import com.acma.properties.models.ConstructionType;
import com.acma.properties.repo.ConstructionTypeRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * ConstructionType Service.
 */
@Service
@Slf4j
public class ConstructionTypeServiceImpl implements ConstructionTypeService {
  private ConstructionTypeRepo constructionTypeRepo;
  private ModelMapper mapper;

  public ConstructionTypeServiceImpl(ConstructionTypeRepo constructionTypeRepo, ModelMapper mapper) {
    this.constructionTypeRepo = constructionTypeRepo;
		this.mapper = mapper;
	}

	@Override
	public ConstructionTypeBean createConstructionType(ConstructionTypeBean cTypeBean) {
		ConstructionType ctype= mapper.map(cTypeBean, ConstructionType.class);
		try {
			ctype = constructionTypeRepo.save(ctype);
			if(StringUtils.hasText(ctype.getId())) {
				log.info("--Construction Type Id--->:\t"+ctype.getId());
			}else {
				throw new RuntimeException("Something went wrong while the Construction Type is Creating...");
			}
		}catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return mapper.map(ctype, ConstructionTypeBean.class);
	}

	@Override
	public ConstructionTypeBean getConstructionByType(String type) {
		log.info("--Construction Type By Type--->:\t"+type);
		
		
		ConstructionType ctype = constructionTypeRepo.fetchCoonstructionByType(type).stream().findFirst().get();
		
		return mapper.map(ctype,ConstructionTypeBean.class);
	}

	@Override
	public ConstructionTypeBean getConstructionById(String id) {
		log.info("--Construction Type Id--->:\t"+id);
		return mapper.map(constructionTypeRepo.findById(id).get(),ConstructionTypeBean.class);
	}

}
