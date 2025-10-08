package com.main.CrediLink.integration.sippulse.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.main.CrediLink.integration",
        entityManagerFactoryRef = "mariaDbEntityManager",
        transactionManagerRef = "mariaDbTransactionManager"
)
public class MariaDbConfig {

    @Bean
    @ConfigurationProperties("sippulse.datasource")
    public DataSourceProperties mariaDbDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource mariaDbDataSource() {
        return mariaDbDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Bean(name = "mariaDbEntityManager")
    public LocalContainerEntityManagerFactoryBean mariaDbEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(mariaDbDataSource());
        em.setPackagesToScan("com.main.CrediLink.integration");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setShowSql(false);
        em.setJpaVendorAdapter(vendorAdapter);

        HashMap<String, Object> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "none");
        jpaProperties.put("hibernate.format_sql", "true");
        jpaProperties.put("hibernate.use_sql_comments", "false");
        em.setJpaPropertyMap(jpaProperties);

        return em;
    }
}
