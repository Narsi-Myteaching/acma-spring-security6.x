/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.PropertyQuoteBean;

/**
 * 
 */
public interface PropertyQuoteService {

	PropertyQuoteBean createQuote(PropertyQuoteBean propertyQuoteBean);
	PropertyQuoteBean getQuoteById(int id);
	List<PropertyQuoteBean> getAllPropertyQuotes();
	List<PropertyQuoteBean> deleteQuote(int id);
}
