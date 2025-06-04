package kr.ac.hansung.cse.SpringDataJpaAndSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    // 처음 로그인 창, 로그인 창에서 값을 입력한 다음 리다이렉트되는 로그인 창
    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false) String emailNotFound,
                            @RequestParam(required = false) String passwordNotMatch,
                            @RequestParam(required = false) String email,
                            Model model) {
        if (emailNotFound != null) {
            model.addAttribute("emailNotFound", true);
            model.addAttribute("email", email);
        }
        else if (passwordNotMatch != null) {
            model.addAttribute("passwordNotMatch", true);
            model.addAttribute("email", email);
        }
        return "login";
    }

}