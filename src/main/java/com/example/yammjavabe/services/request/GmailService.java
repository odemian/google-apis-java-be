package com.example.yammjavabe.services.request;

import com.example.yammjavabe.services.user.UserGmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

/**
 * This is a Spring service that extends user token bound UserGmailService
 * This way we can use all the functions as both part of Spring lifecycle (see GmailController) and standalone (see MergeService)
 *
 * This service is RequestScope, this way it can be bound to the user token passed as header
 */
@Service
@RequestScope
public class GmailService extends UserGmailService {
    @Autowired
    public GmailService (HttpServletRequest request) throws Exception {
        super(request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
