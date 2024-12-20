package hcmute.infrastruture.security.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //Lấy tên client OAuth2
        String clientName = userRequest.getClientRegistration().getClientName();
        //Tải thông tin người dùng từ OAuth2
        OAuth2User user = super.loadUser(userRequest);
        return new CustomOAuth2User(user, clientName);
    }
}
