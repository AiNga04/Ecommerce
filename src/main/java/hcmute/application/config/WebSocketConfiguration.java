package hcmute.application.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;

import org.springframework.core.Ordered;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;

import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.security.messaging.context.AuthenticationPrincipalArgumentResolver;
import org.springframework.core.annotation.Order;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Configuration
@RequiredArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
//    private final HttpSessionHandshakeInterceptor httpSessionHandshakeInterceptor;
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        registry.enableSimpleBroker("/chat", "/notification");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        registry.addEndpoint("/ws").setAllowedOrigins("*");
//        registry.addEndpoint("/ws")
//                .setHandshakeHandler(defaultHandshakeHandler())
//                .setAllowedOriginPatterns("*")
//                .withSockJS()
//                .setInterceptors(httpSessionHandshakeInterceptor);
//    }
//
//    private DefaultHandshakeHandler defaultHandshakeHandler() {
//        return new DefaultHandshakeHandler() {
//            @Override
//            protected Principal determineUser(
//                    @NotNull ServerHttpRequest request,
//                    @NotNull WebSocketHandler wsHandler,
//                    @NotNull Map<String, Object> attributes
//            ) {
//                Principal principal = request.getPrincipal();
//                if (principal == null) {
//                    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
//                    authorities.add(new SimpleGrantedAuthority("ANONYMOUS"));
//                    principal = new AnonymousAuthenticationToken("WebsocketConfiguration", "anonymous", authorities);
//                }
//                return principal;
//            }
//        };
//    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new AuthenticationPrincipalArgumentResolver());
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }
}