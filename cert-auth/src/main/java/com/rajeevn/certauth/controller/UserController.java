package com.rajeevn.certauth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController
{
    // @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/user")
    public String user(Model model, Principal principal)
    {
        model.addAttribute("username", "admin");
        return "user";
    }
}
