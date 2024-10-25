package br.itb.project.uniaoVoluntaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String remetente;
	
	public String enviarEmailTexto(String destinatario, String assutno, String mensagem) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(remetente);
			simpleMailMessage.setTo(destinatario);
			simpleMailMessage.setSubject(assutno);
			simpleMailMessage.setText(mensagem);
			javaMailSender.send(simpleMailMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}
	
	
	public String faleconosco(String destinatario, String assutno, String mensagem) {
		try {
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(destinatario);
			simpleMailMessage.setTo(remetente);
			simpleMailMessage.setSubject(assutno);
			simpleMailMessage.setText(mensagem);
			javaMailSender.send(simpleMailMessage);
			return "Email enviado";
		}catch(Exception e) {
			return "Erro ao enviar email" + e.getLocalizedMessage();
		}
	}
}
