package org.example.ecommerce.contract.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Cấu hình SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())  // Tắt CSRF nếu sử dụng API REST
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll()  // Cho phép tất cả các yêu cầu mà không cần xác thực
                );

        return http.build();  // Xây dựng SecurityFilterChain
    }

    // Cấu hình PasswordEncoder (nếu cần mã hóa mật khẩu cho các dịch vụ xác thực)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Mã hóa mật khẩu bằng BCrypt
    }

    // Cấu hình UserDetailsService (Nếu cần, nhưng không sử dụng cho việc xác thực token)
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.withUsername(username)
                .password(passwordEncoder().encode("password"))  // Mật khẩu ví dụ
                .roles("USER")  // Vai trò người dùng
                .build();
    }
}
