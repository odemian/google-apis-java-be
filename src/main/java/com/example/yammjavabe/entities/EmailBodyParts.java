package com.example.yammjavabe.entities;

public class EmailBodyParts {
    private String plain;
    private String html;

    public String getPlain() {
        return plain;
    }

    public EmailBodyParts setPlain(String plain) {
        this.plain = plain;
        return this;
    }

    public String getHtml() {
        return html;
    }

    public EmailBodyParts setHtml(String html) {
        this.html = html;
        return this;
    }
}
