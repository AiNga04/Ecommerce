package hcmute.infrastruture.security;

import hcmute.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    @Getter
    private String userId;
    private UserEntity user;

    public CustomUserDetails(
            String username, String password, Collection<? extends GrantedAuthority> authorities,
            String userId
    ) {
        // Khởi tạo các thuộc tính khác
        this.userId = userId;
    }

    public UserEntity getUser() {
        return this.user;
    }
    public CustomUserDetails(UserEntity user) {
        this.user = user;
    }

    // Các phương thức khác của UserDetails

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities().stream().map(au -> new SimpleGrantedAuthority(au.getRole().getId()))
                   .peek(System.out::println).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled();
    }
}