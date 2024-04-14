package com.mail.mail.model;

import java.util.List;

public class MultipleRecievers {
    List<String> to;
    String subject;
    String message;

    public MultipleRecievers(List<String> to, String subject, String message) {
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    public List<String> getTo() {
        return this.to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
