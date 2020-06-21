/**
 * 
 */
package com.itheima.security.springboot.properties;

/**
 * @author zhailiang
 *
 */
public class QQProperties extends SocialProperties {
	
	private String providerId = "qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
	
}
