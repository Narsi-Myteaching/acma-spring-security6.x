/**
 * 
 */
package com.acma.properties.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.acma.properties.beans.CoverageTypeBean;
import com.acma.properties.beans.EmailRequestBean;
import com.acma.properties.beans.NotificationsBean;
import com.acma.properties.beans.PolicyTypeBean;
import com.acma.properties.beans.PropertyQuoteBean;
import com.acma.properties.beans.QuoteTypeBean;
import com.acma.properties.config.AgentsMgmtSvcFeign;
import com.acma.properties.config.NotificationsMgmtSvcFeign;
import com.acma.properties.config.PropertiesMgmtSvcFeign;
import com.acma.properties.events.LeadsProducer;
import com.acma.properties.models.PropertyLead;
import com.acma.properties.models.PropertyLeadDetails;
import com.acma.properties.models.PropertyQuote;
import com.acma.properties.models.PropertyQuoteDetails;
import com.acma.properties.repos.CoverageTypeRepo;
import com.acma.properties.repos.PolicyTypeRepo;
import com.acma.properties.repos.PropertyDetailsRepo;
import com.acma.properties.repos.PropertyLeadDetailsRepo;
import com.acma.properties.repos.PropertyLeadRepo;
import com.acma.properties.repos.PropertyQuoteRepo;
import com.acma.properties.repos.QuoteTypeRepo;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 */
@Service
@AllArgsConstructor
//@RequiredArgsConstructor
@Slf4j
public class PropertyQuoteServiceImpl implements PropertyQuoteService {

	private PropertyQuoteRepo quoteRepo;

	private QuoteTypeRepo quoteTypeRepo;

	private CoverageTypeRepo coverageTypeRepo;

	private PolicyTypeRepo policyTypeRepo;

	private PropertyDetailsRepo propertyDetailsRepo;

	private PropertyLeadRepo propertyLeadRepo;

	private PropertyLeadDetailsRepo leadDetailsRepo;

	private LeadsProducer leadsProducer;

	private ModelMapper mapper;

	private PropertiesMgmtSvcFeign propertiesMgmtSvcFeign;

	private AgentsMgmtSvcFeign agentsMgmtSvcFeign;

	private NotificationsMgmtSvcFeign notificationsMgmtSvcFeign;

//	@Value("${acma.properties.quote.expiry}")
//	private String quoteExpiryDays;
//	
//	@Value("${acma.properties.lead.expiry}")
//	private String leadExpiryDays;

	@Override
	@Transactional
	public PropertyQuoteBean createQuote(PropertyQuoteBean propertyQuoteBean) {
		log.info("---Creating Property Quote--->");
		log.info(" " + propertyQuoteBean.toString());
		try {
			// PropertyQuote quoteModel = mapBeanToModel(propertyQuoteBean);
			PropertyQuote quoteModel = mapper.map(propertyQuoteBean, PropertyQuote.class);
			quoteModel.setUserInfo(propertyQuoteBean.getUserId());
			quoteModel.setCreatedDate(LocalDate.now());
			quoteModel.setExpDate(LocalDate.now().plusDays(60));
			log.info("property quote model is " + quoteModel.toString());
			quoteModel = quoteRepo.save(quoteModel);
			if (!ObjectUtils.isEmpty(quoteModel) && quoteModel.getId() > 0) {
				log.info("---DB Property Quote Model is--> " + quoteModel.toString());
				PropertyQuoteDetails quoteDetails = propertyDetailsRepo.save(mapBeanToquoteDetails(propertyQuoteBean));
				quoteDetails.setPropertyQuote(quoteModel);

				/**
				 * A Special Case to check The lead exp is greater than the quote expiry
				 */
				LocalDate leadExpDate = LocalDate.now().plusDays(30);
				if (leadExpDate.isAfter(quoteModel.getExpDate())) {
					leadExpDate = quoteModel.getExpDate();
				}

				/**
				 * Create a Lead associated to a Quote
				 */
//				PropertyLead lead = PropertyLead.builder().propertyInfo(quoteModel.getPropertyInfo())
//						.userInfo(quoteModel.getUserInfo()).status("Active").startDate(LocalDate.now())
//						.expDate(LocalDate.now().plusDays(30)).build();
//				lead.setCreatedBy("owner");
//				lead.setCreatedDate(new Date());
//				lead.setModifiedBy("owner");
//				lead.setModifiedDate(new Date());
//				propertyLeadRepo.save(lead);
//
//				PropertyLeadDetails leadDetails = new PropertyLeadDetails();
//				leadDetails.setLead(lead);
//				leadDetails.setQuote(quoteModel);
//				leadDetailsRepo.save(leadDetails);

				/**
				 * when lead and lead details were created, produce lead as an event to the
				 * kafka topic
				 */
//				if ((lead != null && lead.getId() > 0) && (leadDetails != null && leadDetails.getId() > 0)) {
//					boolean isLeadSent = leadsProducer.produceLead(lead);
//					log.info("does lead sent?:\t" + isLeadSent);
//					if (!isLeadSent) {
//						// Notification to the all agents who are in the zipcode of the property
//						String propInfo = propertiesMgmtSvcFeign.fetchPropertyInfoById(quoteModel.getPropertyInfo());
//						JSONObject jsonObj = new JSONObject(propInfo);
//						String pinCode = null;
//						if (jsonObj.has("addressBean")) {
//							JSONObject addressObj = jsonObj.getJSONObject("addressBean");
//							pinCode = addressObj.getString("zipCode");
//							log.info("ZipCode is " + pinCode);
//						}
//						if (StringUtils.hasText(pinCode)) {
//							List<String> agentsEmails = agentsMgmtSvcFeign.fetchAgentsByZipCode(pinCode);
//							log.info("List of agent emails are:\t" + agentsEmails);
//							List<EmailRequestBean> toList = new ArrayList<>();
//							if (!CollectionUtils.isEmpty(agentsEmails)) {
//								agentsEmails.stream().forEach(agentEmail -> {
//									EmailRequestBean emailReBean = new EmailRequestBean();
//									emailReBean.setEmail(agentEmail);
//									toList.add(emailReBean);
//								});
//							}
//							String emailSubject = "A New Lead has been Arrived";
//							String emailContentType = "text/html";
//							NotificationsBean notificationsBean = new NotificationsBean();
//							notificationsBean.setEmailContentType(emailContentType);
//							notificationsBean.setEmailSubject(emailSubject);
//							notificationsBean.setToList(toList);
//							notificationsMgmtSvcFeign.sendNotifications(notificationsBean,lead.getId());
//						}
//					}
//				}

				return mapModelToBean(quoteModel, quoteDetails);
			} else {
				log.warn("--Something went wring while Quote is creating-->");
				throw new RuntimeException("Something went wring while Quote is creating");
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			e.printStackTrace();
			throw new RuntimeException(e.getLocalizedMessage());
		}

	}

	@Override
	public PropertyQuoteBean getQuoteById(int id) {
		log.info("--Get Quote By Id--->:\t" + id);
		try {
			PropertyQuote quoteModel = quoteRepo.findById(id).get();
			if (!ObjectUtils.isEmpty(quoteModel) && quoteModel.getId() > 0) {
				log.info("---DB Quote Model is--> " + quoteModel.toString());
				return mapModelToBean(quoteModel, null);
			} else {
				log.warn("--No Quote  Found with the give Id-->");
				throw new RuntimeException("No Quote  Found with the give Id " + id);
			}
		} catch (Exception e) {
			log.error("---" + e.getLocalizedMessage() + "--->");
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}

	@Override
	public List<PropertyQuoteBean> getAllPropertyQuotes() {
		log.info("--GetAll Property Quotes--->");
		List<PropertyQuoteBean> quoteBeanList = new ArrayList<>();
		try {
			List<PropertyQuote> quoteModelsList = quoteRepo.findAll();
			if (!CollectionUtils.isEmpty(quoteModelsList)) {
//				return quoteModelsList.stream().map(quoteModel -> mapModelToBean(quoteModel, null))
//						.collect(Collectors.toList());
				quoteModelsList.stream().forEach(quoteModel ->{
					String propInfo = propertiesMgmtSvcFeign.fetchPropertyInfoById(quoteModel.getPropertyInfo());
					JSONObject jsonObj = new JSONObject(propInfo);
					String pinCode = null;
					if (jsonObj.has("addressBean")) {
						JSONObject addressObj = jsonObj.getJSONObject("addressBean");
						pinCode = addressObj.getString("zipCode");
						log.info("ZipCode is " + pinCode);
						quoteModel.setPropertyInfo(addressObj.getString("address1"));
						quoteBeanList.add(mapModelToBean(quoteModel, null));
					}
				});
			}
//			else {
//				log.warn("<--No Property Quotes Found--");
//				throw new RuntimeException("No Property Quotes Found ");
//			}
		} catch (Exception e) {
			log.error("<---" + e.getLocalizedMessage() + "--");
			throw new RuntimeException(e.getLocalizedMessage());
		}
		return quoteBeanList;
	}

	@Override
	@Transactional
	public List<PropertyQuoteBean> deleteQuote(int id) {
		List<PropertyQuoteDetails> propertyQuotesList = propertyDetailsRepo.findAllQuotesByQuote(id);
		if (!CollectionUtils.isEmpty(propertyQuotesList)) {
			PropertyQuoteDetails propertyQuoteDetails = propertyQuotesList.get(0);
			propertyDetailsRepo.delete(propertyQuoteDetails);

		}

		quoteRepo.deleteById(id);
		return getAllPropertyQuotes();
	}

	private PropertyQuoteDetails mapBeanToquoteDetails(PropertyQuoteBean propertyQuoteBean) {
		PropertyQuoteDetails quoteDetails = new PropertyQuoteDetails();
		quoteDetails.setQuoteType(
				Optional.ofNullable(quoteTypeRepo.findById(propertyQuoteBean.getQuoteType().getId()).get()).get());
		quoteDetails.setCoverageType(Optional
				.ofNullable(coverageTypeRepo.findById(propertyQuoteBean.getCoverageType().getId()).get()).get());
		quoteDetails.setPolicyType(
				Optional.ofNullable(policyTypeRepo.findById(propertyQuoteBean.getPolicyType().getId()).get()).get());
		return quoteDetails;
	}

	private PropertyQuoteBean mapModelToBean(PropertyQuote quoteModel, PropertyQuoteDetails propertyQuoteDetails) {
		PropertyQuoteBean quoteBean = mapper.map(quoteModel, PropertyQuoteBean.class);
		quoteBean.setUserId(quoteModel.getUserInfo());
		if (propertyQuoteDetails == null) {
			List<PropertyQuoteDetails> propertyQuotesList = propertyDetailsRepo
					.findAllQuotesByQuote(quoteModel.getId());
			if (!CollectionUtils.isEmpty(propertyQuotesList)) {
				propertyQuoteDetails = propertyQuotesList.get(0);
			}
		}
		if (propertyQuoteDetails != null) {
			quoteBean.setQuoteType(mapper.map(propertyQuoteDetails.getQuoteType(), QuoteTypeBean.class));
			quoteBean.setCoverageType(mapper.map(propertyQuoteDetails.getCoverageType(), CoverageTypeBean.class));
			quoteBean.setPolicyType(mapper.map(propertyQuoteDetails.getPolicyType(), PolicyTypeBean.class));
		}

		return quoteBean;
	}

}
