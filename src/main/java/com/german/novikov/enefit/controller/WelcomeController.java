package com.german.novikov.enefit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class WelcomeController {
    @RequestMapping("/")
    public void redirectToSwaggerUserInterface(HttpServletResponse response) throws IOException {
        response.sendRedirect("swagger-ui.html");
    }
}
