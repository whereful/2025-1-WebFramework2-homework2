package kr.ac.hansung.cse.SpringDataJpaAndSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToProducts() {
        return "redirect:/products";
    }
}