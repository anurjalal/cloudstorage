package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageNotFoundController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "page_not_found";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
