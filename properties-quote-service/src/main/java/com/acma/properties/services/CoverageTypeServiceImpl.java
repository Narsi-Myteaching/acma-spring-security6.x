package com.acma.properties.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.acma.properties.beans.CoverageTypeBean;
import com.acma.properties.beans.PerilsTypeBean;
import com.acma.properties.models.CoverageToPerilsType;
import com.acma.properties.models.CoverageType;
import com.acma.properties.models.PerilsType;
import com.acma.properties.repos.CoverageTypeRepo;
import com.acma.properties.repos.PerilsTypeRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CoverageTypeServiceImpl implements CoverageTypeService {

	private CoverageTypeRepo coverageTypeRepo;

	private PerilsTypeRepo perilsTypeRepo;

	private ModelMapper mapper;

	@Override
	public CoverageTypeBean createCoverage(CoverageTypeBean coverageTypeBean) {
		log.info("---Creating CoverageType--->");
		log.info(" " + coverageTypeBean.toString());
		
		try {
			CoverageType coverageTypeModel = mapBeanToEntity(coverageTypeBean);
			log.info("coverageTypeModel mapped is " + coverageTypeModel.toString());
			coverageTypeModel = coverageTypeRepo.save(coverageTypeModel);
			if (!ObjectUtils.isEmpty(coverageTypeModel) && coverageTypeModel.getId() > 0) {
				log.info("---DB CoverageTypeModel Model is--> " + coverageTypeModel.toString());
				return mapEntityToBean(coverageTypeModel);
				
			} else {
				log.warn("--Something went wring while Coverage Type is creating-->");
				throw new RuntimeException("Something went wring while Coverage Type is creating");
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			e.printStackTrace();
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public CoverageTypeBean getCoverageById(int id) {
		log.info("--Get Coverage Type By Id--->:\t" + id);
		try {
			CoverageType coverageTypeModel = coverageTypeRepo.findById(id).get();
			if (!ObjectUtils.isEmpty(coverageTypeModel) && coverageTypeModel.getId() > 0) {
				log.info("---DB CoverageTypeModel Model is--> " + coverageTypeModel.toString());
				return mapper.map(coverageTypeModel, CoverageTypeBean.class);
			} else {
				log.warn("--No Coverage Type Found with the give Id-->");
				throw new RuntimeException("No Coverage Type Found with the give Id " + id);
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<CoverageTypeBean> getAllCoverageTypes() {
		log.info("--GetAll Coverage Types--->");
		try {
			List<CoverageType> coverageTypeModelsList = coverageTypeRepo.findAll();
			if (!CollectionUtils.isEmpty(coverageTypeModelsList)) {
				return coverageTypeModelsList.stream()
						.map(coverageTypeModel -> mapper.map(coverageTypeModel, CoverageTypeBean.class))
						.collect(Collectors.toList());
			} else {
				log.warn("--No Coverage Types Found-->");
				throw new RuntimeException("No Coverage Types Found ");
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	private CoverageType mapBeanToEntity(CoverageTypeBean coverageTypeBean) {
		CoverageType coverageTypeModel = mapper.map(coverageTypeBean, CoverageType.class);
		if (!CollectionUtils.isEmpty(coverageTypeBean.getPerilsList())) {
			List<CoverageToPerilsType> coverageToPerilsList = new ArrayList<>();
			coverageTypeBean.getPerilsList().stream().forEach(perilsType->{
				CoverageToPerilsType coverageToPerilsType = new CoverageToPerilsType();
				PerilsType savedPerils =  perilsTypeRepo.findById(perilsType.getId()).get();
				coverageToPerilsType.setPerilsTypeModel(savedPerils);
				coverageToPerilsType.setCoverageTypeModel(coverageTypeModel);
				coverageToPerilsList.add(coverageToPerilsType);
			});
			coverageTypeModel.setPerilsList(coverageToPerilsList);
		}
		
		return coverageTypeModel;
	}
	
	private CoverageTypeBean mapEntityToBean(CoverageType coverageType) {
		CoverageTypeBean coverageTypeBean = mapper.map(coverageType, CoverageTypeBean.class);
		if (!CollectionUtils.isEmpty(coverageType.getPerilsList())) {
			List<PerilsTypeBean> perilsList = new ArrayList<>();
			coverageType.getPerilsList().stream().forEach(coverageToPerisType->{
				PerilsType perilsTypeModel = coverageToPerisType.getPerilsTypeModel();
				PerilsTypeBean perilsTypeBean = mapper.map(perilsTypeModel, PerilsTypeBean.class);
				perilsList.add(perilsTypeBean);
				
			});
			coverageTypeBean.setPerilsList(perilsList);
		}
		
		return coverageTypeBean;
	}
}
