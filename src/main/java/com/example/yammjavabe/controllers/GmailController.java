package com.example.yammjavabe.controllers;

import com.example.yammjavabe.services.request.GmailService;
import com.google.api.services.gmail.model.Draft;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/gmail")
public class GmailController {
    private final GmailService gmailService;

    @Autowired
    public GmailController(GmailService gmailService) {
        this.gmailService = gmailService;
    }

    @GetMapping
    public List<Draft> getAllDrafts () throws IOException {
        return gmailService.getAllDrafts().getDrafts();
    }
}
