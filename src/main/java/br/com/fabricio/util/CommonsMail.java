package br.com.fabricio.util;

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
        email.setSmtpPort(465);
        email.setAuthentication("fluenciaagora@gmail.com", "@Fluencia");
        email.addTo(address, destination); 
        email.setFrom("fluenciaagora@gmail.com", "Fluencia");
        email.setSubject("Importante"); 
        email.setHtmlMsg(html);
        email.send(); 
    }

}