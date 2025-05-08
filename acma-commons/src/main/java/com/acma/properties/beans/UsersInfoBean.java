/**
 * 
 */
package com.acma.properties.beans;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 */
@Data
public class UsersInfoBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8769444137718470314L;
	
	private String userId;
	private String username;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String mobile;

	
}
