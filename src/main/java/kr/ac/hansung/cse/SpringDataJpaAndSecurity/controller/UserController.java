package kr.ac.hansung.cse.SpringDataJpaAndSecurity.controller;

import kr.ac.hansung.cse.SpringDataJpaAndSecurity.entity.MyUser;
import kr.ac.hansung.cse.SpringDataJpaAndSecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/userList")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String showUserList(Model model) {
        List<MyUser> users = userService.listAll();
        model.addAttribute("users", users);
        return "user_list"; // user_list.html
    }
}