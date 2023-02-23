package br.ufg.chamadaeletronica;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
(
		entityManagerFactoryRef = "chamadaEntityManagerFactory",
		transactionManagerRef = "chamadaTransactionManager",
		basePackages = { "br.ufg.chamadaeletronica.repository" }
)
public class ChamadaDbConfig {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.chamada")
	public DataSourceProperties chamadaDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.chamada")
	public DataSource chamadaDatasource() {
		return chamadaDataSourceProperties().initializeDataSourceBuilder().build();
	}
	
	@Primary
	@Bean(name = "chamadaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean entityManager = builder.dataSource(chamadaDatasource()).packages("br.ufg.chamadaeletronica.entity").persistenceUnit("chamada").build();
		Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		
		entityManager.setJpaProperties(properties);
		return entityManager;
	}
	
	@Primary
	@Bean(name = "chamadaTransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("chamadaEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	
}
