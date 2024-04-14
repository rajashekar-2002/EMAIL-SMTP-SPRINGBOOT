package com.mail.mail.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mail.mail.model.MailModel;
import com.mail.mail.model.MultipleRecievers;
import com.mail.mail.service.JavaMail;

import jakarta.mail.MessagingException;  

@Controller
public class MailController {

    private JavaMail mailService;

    public MailController(JavaMail mailService) {
        this.mailService = mailService;
    }


    @PostMapping("/sendmail")
    public ResponseEntity sendMail(@RequestBody MailModel model){
        mailService.sendEmail(model.getTo(), model.getSubject(), model.getMessage());
        return ResponseEntity.ok("success");
    }


    @PostMapping("/attach")
    public ResponseEntity sendMailWithAttachment(@RequestBody MailModel model) throws MessagingException{
        mailService.sendMAilWithAttachment(model.getTo(),model.getSubject(),model.getMessage());
        return ResponseEntity.ok("success");
    }



        /* {
            "to":["raj**********@gmail.com","gani**********@gmail.com"],
            "subject" : "hi guys ......",
            "message" : "this is message"
        } */
    @PostMapping("/sendMailList")
    public ResponseEntity sendMailMultipleReceivers(@RequestBody MultipleRecievers model){

        mailService.sendEmailMultipleReceivers(model.getTo().toArray(new String[0]), model.getSubject(), model.getMessage());
        return ResponseEntity.ok("success");
    }


    @PostMapping("/sendMailHTML")
    public ResponseEntity sendMailWithHTML(@RequestBody MailModel model) throws MessagingException{

        mailService.sendMailWithHTML(model);
        return ResponseEntity.ok("success");
    }

    @PostMapping("/thymleaf")
    public ResponseEntity sendMailWithThymleaf(@RequestBody MailModel model) throws MessagingException{

        mailService.sendMailWithThymleaf(model);
        return ResponseEntity.ok("success");
    }


}
