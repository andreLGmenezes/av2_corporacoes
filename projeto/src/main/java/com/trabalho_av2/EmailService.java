package com.trabalho_av2;


import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void enviarEmail(String destinatario, String assunto, String corpo) throws jakarta.mail.MessagingException {

        System.out.println("E-mail enviado para " + destinatario);
    }
}
