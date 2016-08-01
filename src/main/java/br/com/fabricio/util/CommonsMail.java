package br.com.fabricio.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class CommonsMail {
     
    public CommonsMail(String destination, String address, String key) throws EmailException {
        sendEmail(destination, address, key);
    }
    
   public void sendEmail(String destination, String address, String key) throws EmailException {
    
    	String html = "	<span>Obrigado pela sua participação no meu curso.</span><br> "
    			+ "<span>Click no link abaixo para descobrir o segredo da fluência.</span><br/>"
    			+ "	<span>Um abraço e nós vemos em breve.</span><br/>"
    			+ " <a href=\"fabricioashua-fabricioashua.rhcloud.com/geofusion/survey?"+key+"\">O segredo da fluência</a></div>";
    	
    	HtmlEmail email = new HtmlEmail();
        email.setHostName("smtp.gmail.com"); 
        email.addTo(address);
        email.setSmtpPort(25);
        email.setFrom("fluenciaagora@gmail.com", "Fluencia");
        email.setSubject("Importante"); 
        email.setHtmlMsg(html);
        email.setSSLOnConnect(true);
        email.setSSLCheckServerIdentity(true);
        email.setStartTLSRequired(true);
        email.setAuthenticator(new DefaultAuthenticator("fluenciaagora@gmail.com", "@Fluencia"));
        email.setTextMsg("Your email client does not support HTML messages");
        email.send(); 
    }

}