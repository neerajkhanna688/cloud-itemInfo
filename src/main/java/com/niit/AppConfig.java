package com.niit;


import java.util.Properties;

import javax.persistence.Id;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@Configuration
@ComponentScan
@EnableTransactionManagement
public class AppConfig {
  
	@Autowired
	private Environment environment;
	
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
    	LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
    	lcemfb.setDataSource(getDataSource());
    	HibernateJpaVendorAdapter hibernateJpaVendorAdapter  = new HibernateJpaVendorAdapter();
    	lcemfb.setJpaVendorAdapter(hibernateJpaVendorAdapter);
    	lcemfb.setJpaProperties(jpaProperties());
    	lcemfb.setPackagesToScan("com.niit.model");
    	return lcemfb;
    }
	
	private Properties jpaProperties(){
		Properties properties = new Properties();
		  properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		return properties;
	}
	
	@Bean
	public DataSource getDataSource(){
		System.out.println("  ENV FROM SERVER COMFIG "+ environment.getProperty("info.foo"));
//		System.out.println(" ---- Pass  "+ this.environment.getProperty("spring.datasoruce.password"));
//		System.exit(00);
			DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
			driverManagerDataSource.setDriverClassName(this.environment.getProperty("spring.datasource.driver-name"));
			driverManagerDataSource.setUsername(this.environment.getProperty("spring.datasource.username"));
			driverManagerDataSource.setPassword(this.environment.getProperty("spring.datasoruce.password"));
			driverManagerDataSource.setUrl(this.environment.getProperty("spring.datasource.url"));
			return driverManagerDataSource;
	}
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
	
	
}