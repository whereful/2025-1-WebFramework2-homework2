package kr.ac.hansung.cse.SpringDataJpaAndSecurity.config;

import kr.ac.hansung.cse.SpringDataJpaAndSecurity.error.AuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    /*
    내가 추가한 코드들
     */

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;


    // BadCredentialsException이 UsernameNotFoundException을 감싸지 않도록 설정한 AuthenticationProvider 등록
    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        provider.setHideUserNotFoundExceptions(false); // 핵심 설정
        return provider;
    }

    //--------------------------------------------------------------------------


    @Autowired
    private UserDetailsService customUserDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    private static final String[] PUBLIC_MATCHERS = {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/about/**",
            "/contact/**",
            "/error/**",
            "/console/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(PUBLIC_MATCHERS).permitAll()
                        .requestMatchers("/signup").permitAll()

                        // GET 요청 시에 파라미터도 허용하려면 **을 붙여야 함
                        .requestMatchers("/login", "login/**").permitAll()

                        // ✅ 로그인 사용자만 접근 가능한 경로
                        .requestMatchers("/products").authenticated()

                        .requestMatchers("/user/**").hasRole("ADMIN")
                        .requestMatchers("/products/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )

                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .failureHandler(authenticationFailureHandler)
                        // true로 설정하여 항상 지정한 URL로 이동하도록 강제
                        .defaultSuccessUrl("/products", true)
                        // .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedPage("/accessDenied")

                )
                .userDetailsService(customUserDetailsService);
               /* .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/**"));*/

        return http.build();
    }
}
