/**
 * 
 */
package com.acma.properties.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.acma.properties.beans.CoverageTypeBean;
import com.acma.properties.beans.PolicyTypeBean;
import com.acma.properties.models.CoverageType;
import com.acma.properties.models.PolicyType;
import com.acma.properties.repos.CoverageTypeRepo;
import com.acma.properties.repos.PolicyTypeRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@Slf4j
@AllArgsConstructor
public class PolicyTypeServiceImpl implements PolicyTypeService {

	private PolicyTypeRepo policyTypeRepo;
	
	private CoverageTypeRepo coverageTypeRepo;
	
	private ModelMapper mapper;
	
	@Override
	@Transactional
	public PolicyTypeBean createPolicy(PolicyTypeBean policyTypeBean) {
		log.info("---Creating PolicyType--->");
		log.info(" " + policyTypeBean.toString());
		try {
			 CoverageType coverageTypeModel = coverageTypeRepo.findById(policyTypeBean.getCovType().getId()).get();
			PolicyType policyTypeModel = mapper.map(policyTypeBean, PolicyType.class);
			policyTypeModel.setCoverageType(coverageTypeModel);
			policyTypeModel = policyTypeRepo.save(policyTypeModel);
			if (!ObjectUtils.isEmpty(policyTypeModel) && policyTypeModel.getId() > 0) {
				log.info("<---DB PolicyType Model is " + policyTypeModel.toString());
				policyTypeBean = mapper.map(policyTypeModel, PolicyTypeBean.class);
				CoverageTypeBean covTypeBean = mapper.map(policyTypeModel.getCoverageType(), CoverageTypeBean.class);
				policyTypeBean.setCovType(covTypeBean);
				return policyTypeBean;
			} else {
				log.warn("--Something went worng while Policy Type is creating-->");
				throw new RuntimeException("Something went worng while Policy Type is creating");
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			e.printStackTrace();
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public PolicyTypeBean getPolicyById(int id) {
		log.info("--Get Policy Type By Id--->:\t" + id);
		try {
			PolicyType policyTypeModel = policyTypeRepo.findById(id).get();
			if (!ObjectUtils.isEmpty(policyTypeModel) && policyTypeModel.getId() > 0) {
				log.info("---DB PolicyType Model is--> " + policyTypeModel.toString());
				return mapper.map(policyTypeModel, PolicyTypeBean.class);
			} else {
				log.warn("--No Policy Type Found with the give Id-->");
				throw new RuntimeException("No Policy Type Found with the give Id " + id);
			}
		}catch (NoSuchElementException nse) {
			String errorMsg = "---No Policy Type Found with the given ID--->:\t"+id;
			log.error(errorMsg);
			throw new RuntimeException(errorMsg);			
		} 
		catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<PolicyTypeBean> getAllPolicyTypes() {
		log.info("--GetAll Policy Types--->");
		try {
			 List<PolicyType> policyTypeModelsList = policyTypeRepo.findAll();
			 if(!CollectionUtils.isEmpty(policyTypeModelsList)) {
				return policyTypeModelsList
						.stream()
						.map(policyTypeModel->mapper.map(policyTypeModel, PolicyTypeBean.class))
						.collect(Collectors.toList());
			 }else {
				 log.warn("--No Policy Types Found-->");
				 throw new RuntimeException("No Policy Types Found ");
			 }
		}catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

}
