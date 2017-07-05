package connect;


import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimePart;
import javax.swing.JOptionPane;

public class SendMailTLS {

	public static void main(String[] args, String mail, String destinatario) {

		final String username = "pedrotorman35@gmail.com";
		final String password = "lins3265";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "25");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("cersolucoeseletricas@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mail));
			message.setSubject("CER - Soluçoes Elétricas");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
	        Multipart multipart = new MimeMultipart();
	        String file = destinatario;
	        file+=".pdf";
	        String fileName = "Orçamento.pdf";
	        String text = "Obrigado pelo orçamento "+destinatario+"!!!\n \n Qualquer dúvida entre em contato.\n Cer - Soluções Elétricas\n Contato: (31)98603-2648 [WHATSAPP]";
	        String html = "<h1><b>" + text + "</b></h1>";
	        //pega o pdf do diretorio
	        System.out.println(mail);
	        DataSource source = new FileDataSource(file);
	        messageBodyPart.setDataHandler(new DataHandler(source));
	        messageBodyPart.setFileName(fileName);
	        //cria uma arquivo.txt
	        MimeBodyPart textPart = new MimeBodyPart();
	        textPart.setText( text, "utf-8" );
			//cria um htm para fazer leitura do txt
	        MimeBodyPart htmlPart = new MimeBodyPart();
		    htmlPart.setContent( html, "text/html; charset=utf-8" );
		    //multiparte é o pacote que vai receber arquivo txt, htm  e o pdf anexado
	        multipart.addBodyPart( textPart );
	        //multipart.addBodyPart( textPart );
	        multipart.addBodyPart(messageBodyPart);
	        //adiciona o pacote a mensagem
	        message.setContent( multipart );
	        System.out.println("Enviando...");
			Transport.send(message);
			System.out.println("Operação realizada com Sucesso!");
			JOptionPane.showMessageDialog(null, "Orçamento encaminhado com sucesso!");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Não foi possivel encaminhar o orçamento!");
		}
	}
}