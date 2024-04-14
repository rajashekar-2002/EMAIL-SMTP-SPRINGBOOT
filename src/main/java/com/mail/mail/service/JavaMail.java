package com.mail.mail.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.mail.mail.model.MailModel;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class JavaMail {

    private JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    public JavaMail(JavaMailSender mailSender,TemplateEngine templateEngine ) {
    this.templateEngine=templateEngine;
        this.mailSender = mailSender;
    }

    


 

    public void sendEmail(String to, String subject, String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("raj**********@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }


    public void sendEmailMultipleReceivers(String[] to, String subject, String body){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("raj**********@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendMAilWithAttachment(String to, String subject, String body) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom("raj**********@gmail.com");
		helper.setTo(to);
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		
		ClassPathResource classPathResource = new ClassPathResource("groww.png");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		mailSender.send(message);

    }



    public void sendMailWithHTML(MailModel user) throws MessagingException {
        MimeMessage  mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + user.getTo());

        String html = "<!doctype html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\"\n" +
                "      xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\"\n" +
                "          content=\"width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>Email</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div>Welcome <b>" + user.getTo() + "</b></div>\n" +
                "\n" +
        "<a href=\"https://www.google.com\" target=\"_blank\">\n" +
        "    <button>Visit Google</button>\n" +
        "</a>\n" +
                "<div> Your username is <b>" + user.getTo() + "</b></div>\n" +
                "</body>\n" +
                "</html>\n";
        

        helper.setText(html, true);
        helper.setTo(user.getTo());
        helper.setFrom("raj**********@gmail.com");
        mailSender.send(mimeMessage);
    }




    public String sendMailWithThymleaf(MailModel user) throws MessagingException {
        Context context = new Context();
        context.setVariable("user", user);

        String process = templateEngine.process("welcome.html", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Welcome " + user.getTo());
        helper.setText(process, true);
        helper.setTo(user.getTo());
        helper.setFrom("raj**********@gmail.com");
        mailSender.send(mimeMessage);
        return "Sent";
    }
}
