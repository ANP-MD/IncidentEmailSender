import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.mail.*;
import javax.mail.internet.*;

public class Email {
  int penitenciar;
  String data_to_send;

  public Email(int penitentiar, String data_to_send) {
    this.penitenciar = penitentiar;
    this.data_to_send = data_to_send;
  }

  private List < String > getEmailList(int inputInteger) {
    Map < Integer, List < String >> emailMap = new HashMap < > ();

    // Add email addresses for each integer

    emailMap.put(1,  Arrays.asList("serviciul_dip@anp.gov.md",  "p1.director@anp.gov.md", "ombudsman@ombudsman.md","proc-tr@procuratura.md"));
    emailMap.put(2,  Arrays.asList("serviciul_dip@anp.gov.md",  "p2.director@anp.gov.md", "ombudsman@ombudsman.md","proc-br@procuratura.md"));
    emailMap.put(3,  Arrays.asList("serviciul_dip@anp.gov.md",  "p3.director@anp.gov.md", "ombudsman@ombudsman.md","proc-lv@procuratura.md"));
    emailMap.put(4,  Arrays.asList("serviciul_dip@anp.gov.md",  "p4.director@anp.gov.md", "ombudsman@ombudsman.md","proc-crs@procuratura.md"));
    emailMap.put(5,  Arrays.asList("serviciul_dip@anp.gov.md",  "p5.director@anp.gov.md", "ombudsman@ombudsman.md","proc-jch@procuratura.md"));
    emailMap.put(6,  Arrays.asList("serviciul_dip@anp.gov.md",  "p6.director@anp.gov.md", "ombudsman@ombudsman.md","proc-jsr@procuratura.md"));
    emailMap.put(7,  Arrays.asList("serviciul_dip@anp.gov.md",  "p7.director@anp.gov.md", "ombudsman@ombudsman.md","proc-hn@procuratura.md"));
    emailMap.put(8,  Arrays.asList("serviciul_dip@anp.gov.md",  "p8.director@anp.gov.md", "ombudsman@ombudsman.md","proc-mb@procuratura.md"));
    emailMap.put(9,  Arrays.asList("serviciul_dip@anp.gov.md",  "p9.director@anp.gov.md", "ombudsman@ombudsman.md","proc-cbu@procuratura.md"));
    emailMap.put(10, Arrays.asList("serviciul_dip@anp.gov.md", "p10.director@anp.gov.md", "ombudsman@ombudsman.md","proc-crs@procuratura.md"));
    emailMap.put(11, Arrays.asList("serviciul_dip@anp.gov.md", "p11.director@anp.gov.md", "ombudsman@ombudsman.md","proc-mbl@procuratura.md"));
    emailMap.put(12, Arrays.asList("serviciul_dip@anp.gov.md", "p12.director@anp.gov.md", "ombudsman@ombudsman.md","proc-mb@procuratura.md"));
    emailMap.put(13, Arrays.asList("serviciul_dip@anp.gov.md", "p13.director@anp.gov.md", "ombudsman@ombudsman.md","proc-cce@procuratura.md"));
    emailMap.put(14, Arrays.asList("testiciul_dip@anp.gov.md", "testdirector@anp.gov.md", "testdsman@ombudsman.md","test@procuratura.md"));
    emailMap.put(15, Arrays.asList("serviciul_dip@anp.gov.md", "p15.director@anp.gov.md", "ombudsman@ombudsman.md","proc-crs@procuratura.md"));
    emailMap.put(16, Arrays.asList("serviciul_dip@anp.gov.md", "p16.director@anp.gov.md", "ombudsman@ombudsman.md","proc-cbu@procuratura.md"));
    emailMap.put(17, Arrays.asList("serviciul_dip@anp.gov.md", "p17.director@anp.gov.md", "ombudsman@ombudsman.md","proc-rz@procuratura.md"));
    emailMap.put(18, Arrays.asList("serviciul_dip@anp.gov.md", "p18.director@anp.gov.md", "ombudsman@ombudsman.md","proc-jor@procuratura.md"));
    
        //emailMap.put(3, Collections.singletonList("email6@example.com"));

    // Input the integer

    // Get the list of email addresses associated with the input integer
    return emailMap.getOrDefault(inputInteger, Collections.emptyList());

  }

  public void sendEmail() {
	    String host = "smtp-mail.outlook.com";
	    final String user = ""; //change accordingly  
	    final String password = ""; //change accordingly  

	    // Get the list of unique email addresses
	    Set<String> uniqueEmails = new HashSet<>(getEmailList(this.penitenciar + 1));

	    for (String email : uniqueEmails) {
	        //
	        String to = email;

	        //Get the session object  
	        Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.trust", "*");
	        props.put("mail.smtp.port", "587");

	        Session session = Session.getDefaultInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(user, password);
	                }
	            });

	        //Compose the message  
	        try {
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(user));
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	            message.setSubject("Leziuni corporale penitenciarul: " + Integer.toString(this.penitenciar + 1));
	            message.setText(this.data_to_send);

	            //send the message  
	            Transport.send(message);

	            System.out.println("message sent successfully...");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}

}