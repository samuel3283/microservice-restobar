package pe.com.maprsoft.facturador.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.WebApplicationInitializer;

import de.codecentric.boot.admin.model.Application;

@Configuration
@ComponentScan("pe.com.maprsoft.facturador.*")
@EnableAutoConfiguration
public class FacturadorApplication extends SpringBootServletInitializer implements
WebApplicationInitializer {
	
	
    @Value("${bd.user}")
    private String user;

    @Value("${bd.password}")
    private String password;
    
    @Value("${bd.server}")
    private String server;

    @Value("${bd.database}")
    private String database;

    /*
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }*/
	
    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);			    
	}

	@Bean
	public DriverManagerDataSource dataSource() {
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://18.219.252.172:3306/cargo";
		datasource.setUsername("root");
		datasource.setPassword("1235789");
				
		/*	  	
		*/
		url = "jdbc:mysql://"+server+":3306/"+database;
		datasource.setUsername(user);
		datasource.setPassword(password);
				
		datasource.setUrl(url);
		
		return datasource;
	}
		
	@Bean
	public JdbcTemplate jdbcTemplateMySql() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
		//jdbcTemplate.setDataSource(dataSourceOrcl());
		return jdbcTemplate;
	}
	 

}
