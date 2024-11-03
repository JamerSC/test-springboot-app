package com.springboot.fxn.testing.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.model.PettyCash;
import com.springboot.fxn.testing.model.Account;
import com.springboot.fxn.testing.service.PettyCashService;
import com.springboot.fxn.testing.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private PettyCashService pettyCashService;

    @GetMapping("/accounts")
    public String indexPage(Model model) {
        List<Account> listOfAccounts = accountService.getAllAccounts();
        model.addAttribute("listOfAccounts", listOfAccounts);
        return "index";
    }

    @GetMapping("/petty-cash")
    public String pettyCashPage(Model model) {
        List<PettyCash> pettyCashRecords = pettyCashService.getAllPettyCash();
        model.addAttribute("pettyCashRecords", pettyCashRecords);
        return "petty-cash";
    }

    @GetMapping("/petty-cash-form")
    public String pettyCashFormPage(Model model) {
        model.addAttribute("pettyCash", new PettyCashDto());
        List<Account> listOfAccounts = accountService.getAllAccounts();
        model.addAttribute("listOfAccounts", accountService.getAllAccounts());
        return "petty-cash-form";
    }

    @PostMapping("/save-petty-cash")
    public String savePettyCash(@ModelAttribute("pettyCash") PettyCashDto pettyCash) {
        pettyCashService.save(pettyCash);
        return "redirect:/test/petty-cash";
    }

    @GetMapping("/testing")
    public String testingPage(Model model) {
        return "testing";
    }
}
