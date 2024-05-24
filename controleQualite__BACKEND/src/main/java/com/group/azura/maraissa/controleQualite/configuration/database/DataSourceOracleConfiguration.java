package com.group.azura.maraissa.controleQualite.configuration.database;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "OracleEntityManagerFactory", basePackages = {
        "com.group.azura.maraissa.controleQualite.repository.oracle" }, transactionManagerRef = "OracleTransactionManager")
public class DataSourceOracleConfiguration {
    @Value("${spring.datasource.oracle.schema}")
    private String Schema_Oracle;

    @Bean(name = "OracledataSource")
    @ConfigurationProperties(prefix = "spring.datasource.oracle")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "OraclejdbcTemplate")
    public JdbcTemplate OraclejdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean(name = "OracleEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                       @Qualifier("OracledataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
                .packages("com.group.azura.maraissa.controleQualite.entities.oracle").persistenceUnit("oracleunit").build();
        em.setJpaVendorAdapter(vendorAdaptor());
        return em;
    }

    @Bean(name = "OracleTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("OracleEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.OracleDialect");
        vendorAdapter.getJpaPropertyMap().put(AvailableSettings.DEFAULT_SCHEMA, Schema_Oracle);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }
}
