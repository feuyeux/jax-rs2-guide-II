package conference.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String RESOURCE_ID = "conference";

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Value("${redirect:http://localhost:8080/client/conference/redirect}")
    private String redirectUri;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("client")
            .resourceIds(RESOURCE_ID)
            .authorizedGrantTypes("authorization_code", "implicit")
            .authorities("ROLE_CLIENT")
            .scopes("read", "write")
            .secret("secret")
        .and()
            .withClient("client-with-redirect")
            .resourceIds(RESOURCE_ID)
            .authorizedGrantTypes("authorization_code", "implicit")
            .authorities("ROLE_CLIENT")
            .scopes("read", "write")
            .secret("secret")
            .redirectUris(redirectUri)
        .and()
            .withClient("my-client-with-registered-redirect")
            .resourceIds(RESOURCE_ID)
            .authorizedGrantTypes("authorization_code", "client_credentials")
            .authorities("ROLE_CLIENT")
            .scopes("read", "trust")
            .redirectUris("http://anywhere?key=value");
    }

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm("conference/client");
    }
}
