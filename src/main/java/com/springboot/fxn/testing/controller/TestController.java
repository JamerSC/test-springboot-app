package com.springboot.fxn.testing.controller;

import com.springboot.fxn.testing.dto.ClientAccountDto;
import com.springboot.fxn.testing.dto.PettyCashDto;
import com.springboot.fxn.testing.service.PettyCashService;
import com.springboot.fxn.testing.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/test")
@AllArgsConstructor
public class TestController {

    @Autowired
    private AccountService accountService;
    @Autowired(required = true)
    private PettyCashService pettyCashService;

    @GetMapping("/accounts")
    public String indexPage(Model model) {
        List<ClientAccountDto> listOfAccounts = accountService.getAllAccounts();
        model.addAttribute("listOfAccounts", listOfAccounts);
        return "accounts";
    }

    @GetMapping("/{id}/view-account")
    public String viewAccount(@PathVariable(value = "id") Long id, Model model) {
        ClientAccountDto account = accountService.getAccountId(id);
        model.addAttribute("account", account);
        return "account-view";
    }

    @GetMapping("/petty-cash")
    public String pettyCashPage(Model model) {
        List<PettyCashDto> pettyCashRecords = pettyCashService.getAllPettyCash();
        model.addAttribute("pettyCashRecords", pettyCashRecords);
        return "petty-cash";
    }

    @GetMapping("/petty-cash-form")
    public String pettyCashFormPage(Model model) {
        model.addAttribute("pettyCash", new PettyCashDto());
        List<ClientAccountDto> listOfAccounts = accountService.getAllAccounts();
        model.addAttribute("listOfAccounts", listOfAccounts);
        return "petty-cash-form";
    }

    @PostMapping("/save-petty-cash")
    public String savePettyCash(@ModelAttribute("pettyCash") PettyCashDto pettyCash) {
        pettyCashService.save(pettyCash);
        return "redirect:/test/petty-cash";
    }

    @GetMapping("/account-form")
    public String accountFormPage(Model model) {
        model.addAttribute("account", new ClientAccountDto());
        return "account-form";
    }

    @PostMapping("/save-account")
    public String saveAccount(@ModelAttribute("account") ClientAccountDto dto) {
        accountService.save(dto);
        return "redirect:/test/accounts";
    }

    @GetMapping("/testing")
    public String testingPage(Model model) {
        return "testing";
    }
}
