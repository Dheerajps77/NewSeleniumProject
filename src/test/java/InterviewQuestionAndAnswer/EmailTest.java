package InterviewQuestionAndAnswer;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailTest {
	
	public static void main(String[] args) throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator("yescust.onbord01@gmail.com", "cysoeihdbloabclr"));
		email.setSSLOnConnect(true);
		email.setFrom("yescust.onbord01@gmail.com");
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("yescust.onbord01@gmail.com");
		email.send();
	}

}
