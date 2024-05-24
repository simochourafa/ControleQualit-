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
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "PostgresEntityManagerFactory", basePackages = {
        "com.group.azura.maraissa.controleQualite.repository.postgres" }, transactionManagerRef = "PostgresTransactionManager")
public class DataSourcePostgreSqlConfiguration {
    @Value("${spring.datasource.postgres.schema}")
    private String schemaPostgreSQL;

    @Primary
    @Bean(name = "postgresjdbcTemplate")
    public JdbcTemplate postgresjdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Primary
    @Bean(name = "PostgresdataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "PostgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("PostgresdataSource") DataSource dataSource) {

        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");

        LocalContainerEntityManagerFactoryBean em = builder.dataSource(dataSource)
                .packages("com.group.azura.maraissa.controleQualite.entities.postgres")
                .persistenceUnit("postgresunit")
                .properties(properties)
                .build();
        em.setJpaVendorAdapter(vendorAdaptor());
        return em;
    }

    @Primary
    @Bean(name = "PostgresTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("PostgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    private HibernateJpaVendorAdapter vendorAdaptor() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQLDialect");
        vendorAdapter.getJpaPropertyMap().put(AvailableSettings.DEFAULT_SCHEMA, schemaPostgreSQL);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }
}
