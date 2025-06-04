package kr.ac.hansung.cse.SpringDataJpaAndSecurity.controller;

import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyRole;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyUser;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/signup")
    public String signup(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 이미 로그인되어 있고, 익명 사용자가 아닌 경우
        if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
            return "redirect:/products";
        }

        MyUser user = new MyUser();
        model.addAttribute("user", user);

        return "signup";
    }

    @PostMapping(value = "/signup")
    public String signupPost(@ModelAttribute("user") MyUser user, Model model) {

        if (registrationService.checkEmailExists(user.getEmail())) {
            model.addAttribute("emailExists", true);
            return "signup";
        }
        else {
            List<MyRole> userRoles = new ArrayList<>();

            MyRole role = registrationService.findByRolename("ROLE_USER");
            userRoles.add(role);

            // 특정 이메일 주소인 경우 ADMIN 역할 추가
            if ("admin@hansung.ac.kr".equals(user.getEmail())) {
                MyRole roleAdmin = registrationService.findByRolename("ROLE_ADMIN");
                userRoles.add(roleAdmin);
            }

            registrationService.createUser(user, userRoles);

            return "redirect:/login?signup=true";
        }
    }
}