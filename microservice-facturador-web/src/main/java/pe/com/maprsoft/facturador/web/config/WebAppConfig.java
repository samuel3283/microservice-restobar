package pe.com.maprsoft.facturador.web.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import com.planetj.servlet.filter.compression.CompressingFilter;

import pe.com.maprsoft.facturador.web.filter.AccessFilter;
import pe.com.maprsoft.facturador.web.filter.LoggingFilter;
import pe.com.maprsoft.facturador.web.view.ExcelViewResolver;



@Configuration
@ComponentScan({ "pe.com.maprsoft.facturador." })
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

    @Value("${mail.protocol}")  // this is to read variable from application.properties
    private String mailProtocol;

    @Value("${mail.smtp.host}")
    private String host;

    @Value("${mail.smtp.port}")
    private Integer port;

    @Value("${mail.support.username}")
    private String userName;

    @Value("${mail.support.password}")
    private String password;

    @Value("${mail.smtp.auth}")
    private String smtpAuth;

    @Value("${mail.debug}")
    private String mailDebug;

	/**
	 * Método configureContentNegotiation
	 * @param configurer
	 */
	@Override
	public void configureContentNegotiation(
			ContentNegotiationConfigurer configurer) {
		
		super.configureContentNegotiation(configurer);
		configurer.favorParameter(true);
		/*
		configurer
        .defaultContentType(MediaType.APPLICATION_JSON)
        .favorPathExtension(true);*/
	}
	
	
    /*
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);

        // Define all possible view resolvers
        List<ViewResolver> resolvers = new ArrayList<>();

        resolvers.add(excelViewResolver());

        resolver.setViewResolvers(resolvers);
        return resolver;
    }
*/
	/**
	 * Método configureContentNegotiation
	 * @return filterRegistrationBean
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		return registrationBean;
	}

	
	/**
	 * Método compressingFilter
	 * @return Filter
	 */
	@Bean
	public Filter compressingFilter() {
		return new CompressingFilter();
	}

	
	/**
	 * Método accessFilterRegistration
	 * @return FilterRegistrationBean
	 * registration.addInitParameter("appKey", "taxiboox");
	 */
	@Bean
	public FilterRegistrationBean accessFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(accessFilter());
		registration.addInitParameter("appKey", "booxagency");
		registration.addUrlPatterns("/rest/*");
		registration.setName("accessFilter");
		return registration;
	}

	/**
	 * Método accessFilter
	 * @return SecurityAccessFilter
	 */
	@Bean(name = "accessfilter")
 	public AccessFilter accessFilter() {
		return new AccessFilter();
 
	}
	
	
	@Bean(name = "loggingFilter")
 	public LoggingFilter loggingFilter() { 
		return new LoggingFilter();
	}
	

	/*  */
    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        javaMailSender.setProtocol(mailProtocol);
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailProperties());

        return javaMailSender;
    }
 
    private Properties getMailProperties() {
    	/*
    	smtpAuth="true";
    	mailDebug="true";
    	 */
    	Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", smtpAuth);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", mailDebug);
        return properties;
    }

    /*
     * Configure View resolver to provide XLS output using Apache POI library to
     * generate XLS output for an object content
     */
    @Bean
    public ViewResolver excelViewResolver() {
        return new ExcelViewResolver();
    }


}
