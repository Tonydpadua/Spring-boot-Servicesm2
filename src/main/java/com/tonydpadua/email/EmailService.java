package com.tonydpadua.email;

import com.tonydpadua.pedido.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHTMLEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);


}
