package com.appfilm.film.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	/*
	 * Definición del Datasource para la conexión a nuestra base de datos. Las
	 * propiedas son establecidas desde el fichero application.properties y
	 * asignadas usando el objeto env.
	 * 
	 */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(env.getProperty("db.driver"));
		ds.setUrl(env.getProperty("db.url"));
		ds.setUsername(env.getProperty("db.username"));
		ds.setPassword(env.getProperty("db.password"));

		return ds;
	}

	/*
	 * Creamos e inicializamos un EntityManagerFactory de JPA
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

		emf.setDataSource(dataSource);

		// Indicamos la ruta donde tiene que buscar las clases con anotaciones
		emf.setPackagesToScan(env.getProperty("entityManager.packagesToScan"));

		// Unimos JPA con Hibernate
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emf.setJpaVendorAdapter(vendorAdapter);

		// Propiedades de Hibernate
		Properties additionalProperties = new Properties();
		additionalProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));

		additionalProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		additionalProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		emf.setJpaProperties(additionalProperties);

		return emf;
	}

	/*
	 * Crear e inicializar un gestor de transacciones
	 */
	@Bean
	public JpaTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

		return transactionManager;

	}

	/*
	 * Creamos un bean que va a ser un postprocessor que ayuda a relanzar
	 * excepciones específicas de cada plataforma en aquellas clases que vamos a
	 * anotar con @Repository
	 */
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	@Autowired
	private Environment env;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private LocalContainerEntityManagerFactoryBean entityManagerFactory;
}