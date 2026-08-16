package com.ecomm.user.service;

public interface MailSender {

	void sendmail(String to,String subject,String body);
}
