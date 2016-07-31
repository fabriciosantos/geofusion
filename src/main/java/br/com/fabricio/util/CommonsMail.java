package br.com.fabricio.util;

import java.util.Set;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class CommonsMail {

    public CommonsMail(Set<String> destination, String subject, String message) throws EmailException {
        sendEmailHtml(destination, subject, message);
    }
    
    public CommonsMail(Set<String> destination, String subject, String message, String fileAddress) throws EmailException {
        sendEmail(destination, subject, message, fileAddress);
    }

    /**
     * send simple email (only text)
     * @throws EmailException
     */
    public void sendEmailHtml(Set<String> destination, String subject, String message) throws EmailException {

    	if ((destination.size() > 0) && !subject.isEmpty() && !message.isEmpty()) {

            HtmlEmail email = new HtmlEmail();
            email.setHostName("");

            for (String address : destination) {
				email.addTo(address);
			}

            email.setFrom("", "");
            email.setSubject(subject);
            email.setMsg(message);
            email.setAuthentication("", "");
            email.setSmtpPort(587);
            email.send();

        }

    }
    
    public void sendEmail(Set<String> destination, String subject, String message, String fileAddress) throws EmailException {
        
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(fileAddress);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("");
        attachment.setName("");
        
        HtmlEmail email = new HtmlEmail();
        email.setHostName("");
        
        for (String address : destination) {
            email.addTo(address);
        }
        
        email.setFrom("", "");
        email.attach(attachment);
        email.setSubject(subject);
        email.setMsg(message);
        email.setAuthentication("", "");
        email.setSmtpPort(587);
        email.send();
    }

}