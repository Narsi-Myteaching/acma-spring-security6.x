package com.acma.properties.services;

import com.acma.properties.beans.PropertyInfoBean;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * Search Service To search the Properties by the zip code.
 */
@Service
//@RequiredArgsConstructor
@Slf4j
public class SearchService {

  private final MongoTemplate template;

  public SearchService(MongoTemplate template) {
    this.template = template;
  }

  public final List<PropertyInfoBean> searchByName(String zipCode) {
    Query query = new Query();
    query.addCriteria(Criteria.where("zipCode").is(zipCode));
    List<PropertyInfoBean> propertyInfoBean = template.find(query, PropertyInfoBean.class);
    return propertyInfoBean;
  }
}