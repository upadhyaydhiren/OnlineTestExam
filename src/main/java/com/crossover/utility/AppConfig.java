package com.crossover.utility;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.jolbox.bonecp.BoneCPDataSource;

/*
 * Author:-Dhiren
 * Date:-26-09-2015
 * purpose of this Class:-This is configuration class in Test Exam Application.
 *
 */
@EnableWebMvc
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.crossover.*" })
@PropertySource({ "classpath:Hibernate.properties", "classpath:DB.properties" })
public class AppConfig extends WebMvcConfigurerAdapter {

	@Value("${hibernate.format_sql}")
	private String hibernateFormatSql;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateDddl;
	@Value("${db.classDriver}")
	private String dbDriverClass;
	@Value("${db.jdbcurl}")
	private String jdbcUrl;
	@Value("${db.username}")
	private String dbUser;
	@Value("${db.password}")
	private String dbPassword;

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder sessionFactoryBuilder = new LocalSessionFactoryBuilder(
				dataSource());
		sessionFactoryBuilder.scanPackages("com.crossover.domain")
				.addProperties(getHibernateProperties());
		return sessionFactoryBuilder.buildSessionFactory();
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass(dbDriverClass);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUser(dbUser);
		dataSource.setPassword(dbPassword);
		dataSource.setIdleMaxAgeInMinutes(240);
		dataSource.setIdleConnectionTestPeriodInMinutes(60);
		dataSource.setMaxConnectionsPerPartition(15);
		dataSource.setMinConnectionsPerPartition(1);
		dataSource.setPartitionCount(3);
		dataSource.setAcquireIncrement(5);
		dataSource.setStatementsCacheSize(100);
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.format_sql", hibernateFormatSql);
		properties.put("hibernate.show_sql", hibernateShowSql);
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.hbm2ddl.auto", hibernateDddl);
		return properties;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactory());
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations(
				"/resources/");
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
