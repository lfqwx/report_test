package com.lfq.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author: 𝓛.𝓕.𝓠
 */
@Configuration
@EnableJpaRepositories(basePackages = "com.lfq.repository.wh", entityManagerFactoryRef = "secondaryEntityManagerFactory", transactionManagerRef = "secondaryTransactionManager")
@EnableTransactionManagement
public class SecondaryDataSourceConfig {

    @Bean(name="secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    /**
     * 处理数据源和数据实体的工厂类，管理者JPA,Entity,数据源
     * @return
     */
    @Bean(name="secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory() {
        HibernateJpaVendorAdapter japVendor = new HibernateJpaVendorAdapter();
        //自己掌控sql
        japVendor.setGenerateDdl(false);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(secondaryDataSource());
        /*设置JPA适配器*/
        entityManagerFactory.setJpaVendorAdapter(japVendor);
        /*设置需要扫描的实体类*/
        entityManagerFactory.setPackagesToScan("com.lfq.entity");
        return entityManagerFactory;
    }

    /**
     * 事务管理类
     */
    @Bean(name="secondaryTransactionManager")
    public PlatformTransactionManager secondaryTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
