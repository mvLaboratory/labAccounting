package com.mvLab.webAccount.web;

import com.mvLab.webAccount.service.MainWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class MainWebController {
    private MainWebService service;

    @Autowired
    public MainWebController(MainWebService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        return "index";
    }
}
