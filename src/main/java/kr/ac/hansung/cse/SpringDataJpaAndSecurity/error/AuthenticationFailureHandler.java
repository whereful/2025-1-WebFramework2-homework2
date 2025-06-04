package kr.ac.hansung.cse.SpringDataJpaAndSecurity.error;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {

        String email = request.getParameter("username");

        // 이메일 존재 안 함
        if (exception instanceof UsernameNotFoundException) {
            getRedirectStrategy().sendRedirect(request, response, "/login?emailNotFound=true&email=" + email);
            // System.out.println("email: " + email);
        }
        // 비밀번호 틀림
        else if (exception instanceof BadCredentialsException){
            getRedirectStrategy().sendRedirect(request, response, "/login?passwordNotMatch=true&email=" + email);
        }
    }
}