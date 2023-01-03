package com.example.yammjavabe.services.request;

import com.example.yammjavabe.services.user.UserGmailService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

@Service
@RequestScope
public class GmailService extends UserGmailService {

    @Autowired
    public GmailService (HttpServletRequest request) throws Exception {
        super(request.getHeader(HttpHeaders.AUTHORIZATION));
    }
}
