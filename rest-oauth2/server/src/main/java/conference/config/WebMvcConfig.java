package conference.config;

import conference.oauth.AccessConfirmationController;
import conference.oauth.AdminController;
import conference.oauth.UserApprovalHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
        ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
        contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");

        MappingJackson2JsonView defaultView = new MappingJackson2JsonView();
        defaultView.setExtractValueFromSingleKeyModel(true);

        ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
        contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
        contentViewResolver.setViewResolvers(Arrays.<ViewResolver>asList(viewResolver));
        contentViewResolver.setDefaultViews(Arrays.<View>asList(defaultView));
        return contentViewResolver;
    }

    @Bean
    public AccessConfirmationController accessConfirmationController(ClientDetailsService clientDetailsService, ApprovalStore approvalStore) {
        AccessConfirmationController accessConfirmationController = new AccessConfirmationController();
        accessConfirmationController.setClientDetailsService(clientDetailsService);
        accessConfirmationController.setApprovalStore(approvalStore);
        return accessConfirmationController;
    }


    @Bean
    public AdminController adminController(TokenStore tokenStore, ConsumerTokenServices consumerTokenServices,
                                           UserApprovalHandler userApprovalHandler) {
        AdminController adminController = new AdminController();
        adminController.setTokenStore(tokenStore);
        adminController.setTokenServices(consumerTokenServices);
        adminController.setUserApprovalHandler(userApprovalHandler);
        return adminController;
    }

    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
