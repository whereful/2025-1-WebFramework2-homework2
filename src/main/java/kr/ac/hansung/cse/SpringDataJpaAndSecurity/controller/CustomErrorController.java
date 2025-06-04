package kr.ac.hansung.cse.SpringDataJpaAndSecurity.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // 로그인된 사용자 포함 404, 500 등 에러일 때 보여줄 페이지
        return "error_page"; // error_page.html (뷰 이름)
    }


    @RequestMapping("/accessDenied")
    public String handleAccessError() {
        return "403";
    }

}