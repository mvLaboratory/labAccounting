package com.mvLab.webAccount.web;

import com.mvLab.account.DB_Manager;
import com.mvLab.account.catalogs.reagents.ReagentCatalog;
import com.mvLab.account.reports.BalanceReport;
import com.mvLab.webAccount.service.MainWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class MainWebController {
    private MainWebService service;
    private final Logger logger = LoggerFactory.getLogger(MainWebController.class);

    @Autowired
    public MainWebController(MainWebService service) {
        this.service = service;
    }

    @RequestMapping(value = "/empty", method = RequestMethod.GET)
    public String index(Map<String, Object> model) {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        DB_Manager.initialize();

        logger.debug("index() is executed!");
        List<BalanceReport> reagentBalance = DB_Manager.getInstance().readReport(new BalanceReport());

        reagentBalance.forEach(x -> logger.debug(x.toString()));

        //return back to index.jsp
        ModelAndView model = new ModelAndView("index");
        model.addObject("balance", reagentBalance);

        return model;

    }
}
