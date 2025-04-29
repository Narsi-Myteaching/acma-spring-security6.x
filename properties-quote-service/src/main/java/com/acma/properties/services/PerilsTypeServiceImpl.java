package com.acma.properties.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.acma.properties.beans.PerilsTypeBean;
import com.acma.properties.models.PerilsType;
import com.acma.properties.repos.PerilsTypeRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class PerilsTypeServiceImpl implements PerilsTypeService {

	private PerilsTypeRepo perilsTypeRepo;
	
	private ModelMapper mapper;
	
	@Override
	public PerilsTypeBean createPerils(PerilsTypeBean perilsTypeBean) {
		log.info("---Creating PerilsType--->");
		log.info(" " + perilsTypeBean.toString());
		try {
			PerilsType PerilsTypeModel = mapper.map(perilsTypeBean, PerilsType.class);
			PerilsTypeModel = perilsTypeRepo.save(PerilsTypeModel);
			if (!ObjectUtils.isEmpty(PerilsTypeModel) && PerilsTypeModel.getId() > 0) {
				log.info("---DB PerilsTypeModel Model is--> " + PerilsTypeModel.toString());
				return mapper.map(PerilsTypeModel, PerilsTypeBean.class);
			} else {
				log.warn("--Something went wring while Perils Type is creating-->");
				throw new RuntimeException("Something went wring while Perils Type is creating");
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public PerilsTypeBean getPerilsById(int id) {
		log.info("--Get Perils Type By Id--->:\t" + id);
		try {
			PerilsType perilsTypeModel = perilsTypeRepo.findById(id).get();
			if (!ObjectUtils.isEmpty(perilsTypeModel) && perilsTypeModel.getId() > 0) {
				log.info("---DB perilsTypeModel Model is--> " + perilsTypeModel.toString());
				return mapper.map(perilsTypeModel, PerilsTypeBean.class);
			} else {
				log.warn("--No Perils Type Found with the give Id-->");
				throw new RuntimeException("No Perils Type Found with the give Id " + id);
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<PerilsTypeBean> getAllPerilsTypes() {
		log.info("--GetAll Perils Types--->");
		try {
			 List<PerilsType> perilsTypeModelsList = perilsTypeRepo.findAll();
			 if(!CollectionUtils.isEmpty(perilsTypeModelsList)) {
				return perilsTypeModelsList
						.stream()
						.map(perilsTypeModel->mapper.map(perilsTypeModel, PerilsTypeBean.class))
						.collect(Collectors.toList());
			 }else {
				 log.warn("--No Perils Types Found-->");
				 throw new RuntimeException("No Perils Types Found ");
			 }
		}catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

}
