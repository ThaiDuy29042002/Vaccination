package com.example.vaccination.config;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    public static final String[] ENDPOINTS_WHITELIST = {
            "/login",
            "/logout",
            "/home",
            "/access-denied"
    };
    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String LOGIN_FAIL_URL = LOGIN_URL + "?error";
    public static final String DEFAULT_SUCCESS_URL = "/home";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("password"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request ->
                                request.requestMatchers(ENDPOINTS_WHITELIST).permitAll() // cho phép truy cập các URL được xác định bởi danh sách ENDPOINTS_WHITELIST mà không cần xác thực
                                        .requestMatchers("/product/**").hasRole("ADMIN")  // Yêu cầu rằng các URL bắt đầu bằng "/product/" phải được truy cập bởi người dùng có vai trò "ADMIN"
//                                .requestMatchers("/user/**").hasRole("USER")
                                        .anyRequest().authenticated() // yêu cầu rằng bất kỳ URL nào khác cần phải được xác thực
                ) // xác định quyền truy cập cho các tài nguyên và URL trong ứng dụng
                .exceptionHandling(customizer -> customizer.accessDeniedHandler(accessDeniedHandler())) // Cấu hình xử lý các ngoại lệ liên quan đến quyền truy cập
                .formLogin(form -> form // Cấu hình xác thực dựa trên biểu mẫu (form-based authentication)
                        .loginPage(LOGIN_URL) // Xác định trang đăng nhập của ứng dụng
                        .loginProcessingUrl(LOGIN_URL) // URL để xử lý quá trình đăng nhập
                        .failureUrl(LOGIN_FAIL_URL) // URL để chuyển hướng sau khi đăng nhập thất bại
                        .usernameParameter(USERNAME) //  Xác định tên của các trường USERNAME trong biểu mẫu HTML
                        .passwordParameter(PASSWORD) // Xác định tên của các trường PASSWORD trong biểu mẫu HTML
                        .defaultSuccessUrl(DEFAULT_SUCCESS_URL)) // URL mặc định sau khi đăng nhập thành công
                .logout(logout -> logout
                        .logoutUrl(LOGOUT_URL) // URL để xử lý quá trình đăng xuất
                        .logoutSuccessUrl(LOGIN_URL + "?logout") // URL mặc định sau khi đăng xuất thành công
                        .invalidateHttpSession(true) // Hủy bỏ phiên làm việc của người dùng sau khi đăng xuất
                        .clearAuthentication(true) // Xóa thông tin xác thực của người dùng sau khi đăng xuất
                        .deleteCookies("JSESSIONID")) // Xóa cookie JSESSIONID sau khi đăng xuất
                .sessionManagement(session -> session // Cấu hình quản lý phiên làm việc
                        .maximumSessions(1) // giới hạn số phiên đăng nhập đồng thời của một người dùng
                        .maxSessionsPreventsLogin(true));
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        AccessDeniedHandlerImpl handler = new AccessDeniedHandlerImpl();
        handler.setErrorPage("/access-denied");
        return handler;
    }

}

