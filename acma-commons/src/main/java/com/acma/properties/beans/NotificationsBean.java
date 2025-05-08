/**
 * 
 */
package com.acma.properties.beans;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 */
@Data
public class NotificationsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -706737490150463847L;

	private List<EmailRequestBean> toList;
	private String emailSubject;
	private String emailContentType;
	private int statusCode;
}
