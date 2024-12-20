package hcmute.application.config;

import hcmute.entity.CartEntity;
import hcmute.entity.UserEntity;
import hcmute.model.enums.UserType;
import hcmute.service.ICartService;
import hcmute.service.IUserService;
import hcmute.infrastruture.security.CustomUserDetails;
import hcmute.infrastruture.security.CustomUserDetailsService;
import hcmute.infrastruture.security.oauth2.CustomOAuth2UserService;
import hcmute.infrastruture.security.oauth2.OAuthLoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final List<String> PUBLIC_APIS = List.of(
            "/ws/**",
            "/home/**", "/security/**", "/api/places", "/security/verify/**", "/oauth2/**",
            "/oauth/authorize", "/**/*.css", "/**/*.js", "/api/v1/payment/**", "/notifications/**"
    );
    @Autowired
    IUserService userService;
    @Autowired
    ICartService cartService;
    @Autowired
    private CustomUserDetailsService service;
    @Autowired
    private CustomOAuth2UserService oauthUserService;
    @Autowired
    private OAuthLoginSuccessHandler oauthLoginSuccessHandler;

    // Phương thức này trả về một bean của PasswordEncoder để sử dụng trong việc mã
    // hóa mật khẩu
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Cơ chế xác thực trong Spring Security
    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // Cấu hình mã hóa mật khẩu
        provider.setPasswordEncoder(passwordEncoder());
        // Lấy thông tin người dùng từ cơ sở dữ liệu
        provider.setUserDetailsService(service);
        return provider;
    }

    // Cấu hình quản lý xác thực
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(getDaoAuthenticationProvider());
    }

    // Cấu hình loại bỏ một số yêu cầu xác thực cho các file tĩnh
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Cấu hình bảo mật HTTP
        http.csrf().disable().cors();
        // Phân quyền
        http.authorizeRequests()
            // Không cần xác thực
            .antMatchers("/", "/login**").permitAll()
            .antMatchers(PUBLIC_APIS.toArray(String[]::new))
            .permitAll()
            // Phải có quyền MANAGER
            .antMatchers("/manager/index", "/manager/**").hasAuthority(UserType.MANAGER.name())
            // Phải có quyền ADMIN
            .antMatchers("/admin/index", "/admin/**", "/admin/branch/**").hasAuthority(UserType.ADMIN.name())
            // Đường dẫn còn phải phải đăng nhập
            .anyRequest().authenticated();

        // Cấu hình trang đăng nhập
        http.formLogin().loginPage("/security/login").usernameParameter("username").passwordParameter("password")
            .loginProcessingUrl("/security/login").successHandler((request, response, authentication) -> {
                String username = authentication.getName();
                Optional<UserEntity> userOpt = userService.findByUsername(username);

                if (userOpt.isPresent()) {
                    UserEntity user = userOpt.get();
                    int userId = user.getId();
                    Optional<CartEntity> cartOpt = cartService.findCartsByUserId(userId);
                    if (cartOpt.isEmpty()) {
                        CartEntity entity = new CartEntity();
                        entity.setTotalPrice(0);
                        entity.setTotalProduct(0);
                        entity.setCustomerByCart(user);
                        cartService.save(entity);
                    }
                    saveUserIdToCookie(request, response, String.valueOf(userId));
                }
                response.sendRedirect("/home");
            }).failureUrl("/security/login");

        // Cấu hình đăng nhập oauth2
        http.oauth2Login().loginPage("/security/login").failureUrl("/security/login").authorizationEndpoint()
            .baseUri("/oauth2/authorization").and().userInfoEndpoint().userService(oauthUserService).and()
            .successHandler(oauthLoginSuccessHandler);

        // Cấu hình remember me
        http.rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // expired after 21 days
            .key("superhumanisnotsuperjustoverpowered").userDetailsService(service).rememberMeCookieName("USER_ID");

        // Cấu hình đăng xuất
        http.logout().logoutUrl("/security/logout").logoutSuccessUrl("/security/logout/success")
            .clearAuthentication(true).invalidateHttpSession(true).deleteCookies("USER_ID");

        // Xử lý khi người dùng không có quyền truy cập
        http.exceptionHandling().accessDeniedPage("/security/unauthorized");
    }

    private void saveUserIdToCookie(HttpServletRequest request, HttpServletResponse response, String userId) {
        Cookie cookie = new Cookie("USER_ID", userId);
        cookie.setMaxAge(604800); // Thời hạn của cookie (tính bằng giây), ví dụ: 7 ngày (7 * 24 * 60 * 60)
        cookie.setPath("/"); // Đường dẫn áp dụng cho cookie, ví dụ: "/" để áp dụng cho toàn bộ trang web
        response.addCookie(cookie);
    }

    private String getUserIdFromUserDetails(UserDetails userDetails) {
        if (userDetails instanceof CustomUserDetails customUserDetails) {
            return customUserDetails.getUserId();
        }
        return null;
    }
}
