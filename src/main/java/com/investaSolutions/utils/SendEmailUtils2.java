package com.investaSolutions.utils;

import java.util.Map;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendEmailUtils2 {
	private static final PropertiesManager properties = PropertiesManager.getInstance();
	private static final Logger logger = LogManager.getLogger(SendEmailUtils.class);

	// Method to send an email with either single or multiple attachments
	public static void sendEmailNow(String hostName, String portID, String senderEmailID, String senderPassword,
			String[] receiverEmailArray, String[] ccEmailArray, String reportPathOrPaths) {
		// Determine whether a single document or multiple documents are being passed
		String[] reportPaths = reportPathOrPaths.contains(",") ? reportPathOrPaths.split(",")
				: new String[] { reportPathOrPaths };

		try {
			// Create a multi-part email
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName(hostName);
			email.setSmtpPort(Integer.parseInt(portID));
			email.setAuthenticator(new org.apache.commons.mail.DefaultAuthenticator(senderEmailID, senderPassword));
			email.setSSLOnConnect(true);
			email.setFrom(senderEmailID);
			email.setSubject(properties.getConfig("EMAIL_SUBJECT"));
			email.setMsg(buildEmailBody());

			// Add recipients
			for (String recipient : receiverEmailArray) {
				email.addTo(recipient);
			}

			// Add CC if provided
			if (ccEmailArray != null) {
				for (String cc : ccEmailArray) {
					email.addCc(cc);
				}
			}

			// Attach report(s)
			attachReports(email, reportPaths);

			// Send email
			email.send();
			logger.info("Email sent successfully to: {}", String.join(", ", receiverEmailArray));

		} catch (Exception e) {
			logger.error("Error while sending email: ", e);
		}
	}

	// Helper method to attach single or multiple reports dynamically
	private static void attachReports(MultiPartEmail email, String[] reportPaths) throws Exception {
		if (reportPaths != null && reportPaths.length > 0) {
			for (String reportPath : reportPaths) {
				EmailAttachment attachment = new EmailAttachment();
				attachment.setPath(reportPath.trim()); // Trim to handle extra spaces
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setDescription("Report File");
				attachment.setName(reportPath.substring(reportPath.lastIndexOf("/") + 1)); // Extract file name
				email.attach(attachment);
			}
		}
	}

	// HTML email with inline images (optional)
	public static void sendHtmlEmail(String hostName, String portID, String senderEmailID, String senderPassword,
			String[] receiverEmailArray, String[] ccEmailArray, String htmlBody, Map<String, String> inlineImages,
			String reportPathOrPaths) {

		// Determine whether a single document or multiple documents are being passed
		String[] reportPaths = reportPathOrPaths.contains(",") ? reportPathOrPaths.split(",")
				: new String[] { reportPathOrPaths };

		try {
			// Create an HTML email
			HtmlEmail email = new HtmlEmail();
			email.setHostName(hostName);
			email.setSmtpPort(Integer.parseInt(portID));
			email.setAuthenticator(new org.apache.commons.mail.DefaultAuthenticator(senderEmailID, senderPassword));
			email.setSSLOnConnect(true);
			email.setFrom(senderEmailID);
			email.setSubject(properties.getConfig("EMAIL_SUBJECT"));
			email.setHtmlMsg(htmlBody);
			email.setTextMsg("Your email client does not support HTML messages");

			// Add recipients
			for (String recipient : receiverEmailArray) {
				email.addTo(recipient);
			}

			// Add CC if provided
			if (ccEmailArray != null) {
				for (String cc : ccEmailArray) {
					email.addCc(cc);
				}
			}

			// Embed inline images
			if (inlineImages != null && !inlineImages.isEmpty()) {
				for (Map.Entry<String, String> entry : inlineImages.entrySet()) {
					email.embed(entry.getValue(), entry.getKey());
				}
			}

			// Attach report(s)
			attachReports(email, reportPaths);

			// Send email
			email.send();
			logger.info("HTML email sent successfully to: {}", String.join(", ", receiverEmailArray));

		} catch (Exception e) {
			logger.error("Error while sending HTML email: ", e);
		}
	}

	// Build the email body dynamically
	private static String buildEmailBody() {
		return properties.getConfig("EMAIL_BODY_LINE1") + properties.getConfig("EMAIL_BODY_LINE2")
				+ properties.getConfig("EMAIL_BODY_LINE3") + properties.getConfig("EMAIL_BODY_LINE4");
	}
}
