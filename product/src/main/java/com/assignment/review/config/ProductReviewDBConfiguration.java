package com.assignment.review.config;

import java.util.HashMap;

import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "reviewEntityManager", transactionManagerRef = "reviewTransactionManager", basePackages = {"com.assignment.review.repository"})
public class ProductReviewDBConfiguration {
	
	Environment env;
	
	@Bean
	@Primary
	@PersistenceContext(unitName = "produts")
	public DataSource revieDataSource() {
		
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/myproduct_db?useSSL=false");
		hikariConfig.setUsername("root");
		hikariConfig.setPassword("Advit$3108");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
		
	}
	
	@Primary
	@Bean(name="reviewEntityManager")
	public LocalContainerEntityManagerFactoryBean reviewEntitiyManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(revieDataSource());
		em.setPackagesToScan("com.assignment.review.persistence.productreviewdb");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		em.setJpaPropertyMap(properties);
		em.setPersistenceUnitName("productReviewDB");
		return em;
	}
	
	@Primary
	@Bean(name="reviewTransactionManager")
	public PlatformTransactionManager reviewTransactionManager() {
		JpaTransactionManager tm = new JpaTransactionManager();
		tm.setEntityManagerFactory(reviewEntitiyManager().getObject());
		return tm;
	}

}
