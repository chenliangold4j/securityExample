/**
 * 
 */
package com.itheima.security.springboot.validate.code.sms;

/**
 * @author zhailiang
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
