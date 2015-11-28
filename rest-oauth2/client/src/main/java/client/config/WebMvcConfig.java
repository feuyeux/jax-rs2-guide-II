package client.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import client.mvc.ConferenceController;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import client.converter.AccessTokenRequestConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

@Configuration
@EnableWebMvc
@PropertySource("classpath:client.properties")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
		ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
		contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		contentViewResolver.setDefaultViews(Arrays.<View> asList(new MappingJacksonJsonView()));
		return contentViewResolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

    @Bean
    public ConferenceController conferenceController(@Qualifier("conferenceRestTemplate")
                                                         RestOperations restTemplate,
                                                     @Qualifier("trustedClientRestTemplate")
                                                         RestOperations trustedClientRestTemplate,
                                                     @Qualifier("unprotectedRestTemplate")
                                                         RestOperations unprotectedRestTemplate) {
        ConferenceController controller = new ConferenceController();
        controller.setRestTemplate(restTemplate);
        controller.setTrustedClientRestTemplate(trustedClientRestTemplate);
        controller.setUnprotectedRestTemplate(unprotectedRestTemplate);
        return controller;
    }

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
		conversionService.setConverters(Collections.singleton(new AccessTokenRequestConverter()));
		return conversionService;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new BufferedImageHttpMessageConverter());
	}

    @Bean
    public RestTemplate unprotectedRestTemplate() {
        return new RestTemplate();
    }

	@Configuration
	@EnableOAuth2Client
	protected static class ResourceConfiguration {

		@Value("${accessTokenUri}")
		private String accessTokenUri;

		@Value("${userAuthorizationUri}")
		private String userAuthorizationUri;

		@Resource
		@Qualifier("accessTokenRequest")
		private AccessTokenRequest accessTokenRequest;

		@Bean
		public OAuth2ProtectedResourceDetails conference() {
			AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
			details.setId("conference/client");
			details.setClientId("client");
			details.setClientSecret("secret");
			details.setAccessTokenUri(accessTokenUri);
			details.setUserAuthorizationUri(userAuthorizationUri);
			details.setScope(Arrays.asList("read", "write"));
			return details;
		}

        @Bean
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2RestTemplate conferenceRestTemplate() {
            return new OAuth2RestTemplate(conference(), new DefaultOAuth2ClientContext(accessTokenRequest));
        }


		@Bean
		public OAuth2ProtectedResourceDetails conferenceRedirect() {
			AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
			details.setId("conference/client-redirect");
			details.setClientId("client-with-redirect");
			details.setClientSecret("secret");
			details.setAccessTokenUri(accessTokenUri);
			details.setUserAuthorizationUri(userAuthorizationUri);
			details.setScope(Arrays.asList("read", "write"));
			details.setUseCurrentUri(false);
			return details;
		}

        @Bean
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2RestTemplate conferenceRedirectRestTemplate() {
            return new OAuth2RestTemplate(conferenceRedirect(), new DefaultOAuth2ClientContext(accessTokenRequest));
        }

		@Bean
		public OAuth2ProtectedResourceDetails trusted() {
			ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
			details.setId("conference/trusted");
			details.setClientId("my-client-with-registered-redirect");
			details.setAccessTokenUri(accessTokenUri);
			details.setScope(Arrays.asList("trust"));
			return details;
		}

		@Bean
		public OAuth2RestTemplate trustedClientRestTemplate() {
			return new OAuth2RestTemplate(trusted(), new DefaultOAuth2ClientContext());
		}

	}

}
