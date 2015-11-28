package conference.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "conference";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().antMatchers("/rest/**", "/oauth/users/**",
                "/oauth/clients/**")
        .and().authorizeRequests()
            .antMatchers("/rest/speakers").access("#oauth2.hasScope('read')")
            .antMatchers("/rest/test").access("#oauth2.hasScope('read')")
            .antMatchers("/rest/trusted/**").access("#oauth2.hasScope('trust')")
                .regexMatchers(HttpMethod.DELETE, "/oauth/users/([^/].*?)/tokens/.*")
                .access("#oauth2.clientHasRole('ROLE_CLIENT') " +
                        "and (hasRole('ROLE_USER') " +
                        "or #oauth2.isClient()) and #oauth2.hasScope('write')")
            .regexMatchers(HttpMethod.GET, "/oauth/clients/.*")
                .access("#oauth2.clientHasRole('ROLE_CLIENT') " +
                        "and #oauth2.isClient() " +
                        "and #oauth2.hasScope('read')");
    }
}
