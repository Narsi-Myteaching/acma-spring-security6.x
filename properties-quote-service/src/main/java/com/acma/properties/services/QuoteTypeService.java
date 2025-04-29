/**
 * 
 */
package com.acma.properties.services;

import java.util.List;

import com.acma.properties.beans.QuoteTypeBean;

/**
 * 
 */
public interface QuoteTypeService {

	QuoteTypeBean createQuote(QuoteTypeBean quoteTypeBean);
	QuoteTypeBean getQuoteById(int id);
	List<QuoteTypeBean> getAllQuoteTypes();
}
