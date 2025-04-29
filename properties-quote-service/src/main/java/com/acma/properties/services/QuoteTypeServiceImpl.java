/**
 * 
 */
package com.acma.properties.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.acma.properties.beans.QuoteTypeBean;
import com.acma.properties.models.QuoteType;
import com.acma.properties.repos.QuoteTypeRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
@Slf4j
public class QuoteTypeServiceImpl implements QuoteTypeService {

	private QuoteTypeRepo quoteTypeRepo;

	private ModelMapper mapper;

	@Override
	public QuoteTypeBean createQuote(QuoteTypeBean quoteTypeBean) {
		log.info("---Creating Quote--->");
		log.info(" " + quoteTypeBean.toString());
		try {
			QuoteType quoteTypeModel = mapper.map(quoteTypeBean, QuoteType.class);
			quoteTypeModel = quoteTypeRepo.save(quoteTypeModel);
			if (!ObjectUtils.isEmpty(quoteTypeModel) && quoteTypeModel.getId() > 0) {
				log.info("---DB QuoteType Model is--> " + quoteTypeModel.toString());
				return mapper.map(quoteTypeModel, QuoteTypeBean.class);
			} else {
				log.warn("--Something went wring while Quote is creating-->");
				throw new RuntimeException("Something went wring while Quote is creating");
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}

	}

	@Override
	public QuoteTypeBean getQuoteById(int id) {
		log.info("--Get Quote Type By Id--->:\t" + id);
		try {
			QuoteType quoteTypeModel = quoteTypeRepo.findById(id).get();
			if (!ObjectUtils.isEmpty(quoteTypeModel) && quoteTypeModel.getId() > 0) {
				log.info("---DB QuoteType Model is--> " + quoteTypeModel.toString());
				return mapper.map(quoteTypeModel, QuoteTypeBean.class);
			} else {
				log.warn("--No Quote Type Found with the give Id-->");
				throw new RuntimeException("No Quote Type Found with the give Id " + id);
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<QuoteTypeBean> getAllQuoteTypes() {
		log.info("--GetAll Quote Types--->");
		try {
			 List<QuoteType> quoteTypeModelsList = quoteTypeRepo.findAll();
			 if(!CollectionUtils.isEmpty(quoteTypeModelsList)) {
				return quoteTypeModelsList
						.stream()
						.map(quoteTypeModel->mapper.map(quoteTypeModel, QuoteTypeBean.class))
						.collect(Collectors.toList());
			 }else {
				 log.warn("--No Quote Types Found-->");
				 throw new RuntimeException("No Quote Types Found ");
			 }
		}catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

}
